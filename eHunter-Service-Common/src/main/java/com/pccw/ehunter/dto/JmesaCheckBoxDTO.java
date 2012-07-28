package com.pccw.ehunter.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.utility.StringUtils;

public class JmesaCheckBoxDTO extends JmesaCriteria implements Serializable{
	private static final long serialVersionUID = 1L;

	private String[] select;

	private String[] currSelect;

	private List<Selection> allSelections = new ArrayList<Selection>();
	
	private boolean selectAll;

	public String[] getSelect() {
		return select;
	}

	public void setSelect(String[] select) {
		this.select = select;
	}

	public String[] getCurrSelect() {
		return currSelect;
	}

	public void setCurrSelect(String[] currSelect) {
		this.currSelect = currSelect;
	}

	public List<Selection> getAllSelections() {
		return allSelections;
	}

	private void setAllSelections(List<Selection> allSelections) {
		this.allSelections = allSelections;
	}
	
	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public void initAllSelectOption(String[] keys){
		if(keys == null || keys.length == 0){
			setAllSelections(new ArrayList<Selection>());
			return ;
		}
		
		List<Selection> temp = new ArrayList<Selection>();
		for(String key : keys){
			Selection selection = new Selection(key, false);
			temp.add(selection);
		}
		setAllSelections(temp);
		
		this.selectAll = false;
	}
	
	public void resetJmesa(){
		this.allSelections = null;
		this.selectAll = false;
	}
	
	public void handleSelected(){
		removeAllSelected(this.currSelect);
		addAllSelected(this.select);
		resetSelectAllFlag();
		this.select = null ;
		this.currSelect = null;
	}

	private void resetSelectAllFlag() {
		List<Selection> selections = this.allSelections;
		if(!CollectionUtils.isEmpty(allSelections)){
			for(Selection selection : selections){
				if(!selection.isChecked()){
					this.selectAll = false;
					break;
				}
				this.selectAll = true;
			}
		}
	}

	private void addAllSelected(String[] keys) {
		if(keys==null || keys.length==0){
			return ;
		}

		for(String key : keys){
			Selection selection = getSelectionByKey(key);
			if(selection != null){		
				selection.setChecked(true);
			}
		}
		
	}

	private void removeAllSelected(String[] keys) {
		if(keys==null || keys.length==0){
			return ;
		}
		
		for(String key : keys){
			Selection selection = getSelectionByKey(key);
			if(selection != null){
				selection.setChecked(false);
			}
		}
	}
	
	private Selection getSelectionByKey(String key){
		List<Selection> selections = this.allSelections;
		if(CollectionUtils.isEmpty(selections) || StringUtils.isEmpty(key)){
			return null;
		}
		
		for(Selection selection : selections){
			if(key.equals(selection.getKey())){
				return selection;
			}
		}
		return null;
	}
	
	public boolean isSelected(String key){
		Selection selection = getSelectionByKey(key);
		return selection==null?false : selection.isChecked();
	}
	
	public void handleSelectAll(){
		if(this.selectAll){
			disSelectAll();
		}else {
			selectAll();
		}
	}

	private void selectAll() {
		List<Selection> selections = this.allSelections;
		if(!CollectionUtils.isEmpty(selections)){
			for(Selection selection : selections){
				selection.setChecked(true);
			}
		}
		this.selectAll = true;
	}

	private void disSelectAll() {
		List<Selection> selections = this.allSelections;
		if(!CollectionUtils.isEmpty(selections)){
			for(Selection selection : selections){
				selection.setChecked(false);
			}
		}
		this.selectAll = false;
	}
}
