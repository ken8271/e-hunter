package com.pccw.ehunter.helper;

import java.io.File;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("contentSearchEngine")
public class ContentSearchEngine {
	
	private String indexPath;
	
	private String srcPath;
	
	@Autowired
	public void setProperties(Properties xmlProcessorConfig){
		indexPath = xmlProcessorConfig.getProperty("resume.path.index");
		srcPath = xmlProcessorConfig.getProperty("resume.path.upload");
	}
	
	public void createIndex(String filePath) throws Exception{
		File src = new File(filePath);
		
		if(!src.exists()) return ;
		
		initial();
		
		Directory dir = FSDirectory.open(new File(indexPath));
		
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36));
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		
		IndexWriter writer = new IndexWriter(dir, iwc);
		
		Document doc = new Document();
		doc.add(null);
		
	}
	
	private void initial(){
		File indexDir = new File(indexPath);
		
		if(!indexDir.exists()){
			indexDir.mkdirs();
		}
	}
}
