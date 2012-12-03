package com.pccw.ehunter.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.pccw.ehunter.constant.ContentSearchConstant;
import com.pccw.ehunter.dto.ContentSearchCriteria;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.PdfUtils;

@Component("contentSearchEngine")
public class ContentSearchEngine {
	
	private Logger logger = LoggerFactory.getLogger(ContentSearchEngine.class);
	
	private String indexPath;
	
	@Autowired
	public void setProperties(Properties xmlProcessorConfig){
		indexPath = xmlProcessorConfig.getProperty("resume.path.index");
	}
	
	public void createIndex(String talentid , String filePath) throws Exception{
		IndexWriter writer = null;
		try {
			File src = new File(filePath);
			
			if(!src.exists()) return ;
			
			initial();
			
			Directory dir = FSDirectory.open(new File(indexPath));
			
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, new IKAnalyzer());
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			
			writer = new IndexWriter(dir, iwc);
			
			Document doc = new Document();
			doc.add(new Field(ContentSearchConstant.FIELD_TALENT_ID, talentid , Field.Store.YES, Field.Index.NO));
			doc.add(new Field(ContentSearchConstant.FIELD_CV_PATH, src.getPath() , Field.Store.YES, Field.Index.NO));
			doc.add(new Field(ContentSearchConstant.FIELD_CV_CONTENT, getContent(filePath),Field.Store.NO, Field.Index.ANALYZED));
			
			writer.addDocument(doc);
		} catch (Exception e) {
			logger.error(">>>>>exception catched (createIndex) , error message : " + e.getMessage());
			throw e;
		} finally {
			if(writer != null){
				writer.close();
				writer = null;
			}
		}
	}
	
	private String getContent(String filePath) throws Exception{
		String type = FileUtils.getExtension(filePath);
		
		if(FileUtils.PDF_FILE_EXT.equals(type)){
			return PdfUtils.parsePdf(filePath);
		} else {
			return "";
		}
	}
	
	private Query getQueryCriterias(QueryParser parser , List<ContentSearchCriteria> criterias) throws Exception{
		BooleanQuery booleanQuery = new BooleanQuery(); 
		
		if(!CollectionUtils.isEmpty(criterias)){
			for(ContentSearchCriteria c : criterias){
				if(ContentSearchCriteria.RANGE_CRITERIA.equals(c.getType())){
					
				}else  if(ContentSearchCriteria.TERM_CRITERIA.equals(c.getType())){
					logger.debug(">>>>> term criteria :" + c.getValue());
					booleanQuery.add(parser.parse(c.getValue()), BooleanClause.Occur.MUST);
				}
			}			
		}
		
		return booleanQuery;
	}
	
	public List<String> handleSearch(List<ContentSearchCriteria> criterias) throws Exception{
		List<String> matches = new ArrayList<String>();
		
		initial();
		
		Directory dir = FSDirectory.open(new File(indexPath));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		
		Analyzer analyzer = new IKAnalyzer();
		
		QueryParser parser = new QueryParser(Version.LUCENE_36, ContentSearchConstant.FIELD_CV_CONTENT, analyzer);
		
		Query query = getQueryCriterias(parser, criterias);
	    TopDocs topDocs = searcher.search(query, 100);
	    
	    ScoreDoc[] hits = topDocs.scoreDocs;  
	    if(hits != null && hits.length != 0){
	    	for (ScoreDoc scoreDoc : hits) {  
	    		Document hitDoc = searcher.doc(scoreDoc.doc);  
	    		matches.add(hitDoc.get(ContentSearchConstant.FIELD_TALENT_ID));
	    	}	    	
	    }
	    
	    return matches;
	}
	
	private  void initial(){
		File indexDir = new File(indexPath);
		
		if(!indexDir.exists()){
			indexDir.mkdirs();
		}
	}

}
