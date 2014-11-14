package com.beyondsoft.thrift.web.pageModel;

import java.util.HashMap;
import java.util.Map;

public class TreeDTO implements java.io.Serializable{
		
		private String id ;
		private String text ;
		private String text2;
		private String text3 ;
		private String text4 ;
		private String text5 ;
		private String text6 ;
		
		public String getText4() {
			return text4;
		}
		public void setText4(String text4) {
			this.text4 = text4;
		}
		public String getText5() {
			return text5;
		}
		public void setText5(String text5) {
			this.text5 = text5;
		}
		public String getText6() {
			return text6;
		}
		public void setText6(String text6) {
			this.text6 = text6;
		}
		private String iconCls ;
		private int checked ;
		private String pid ;
		private Map<String, Object> attributes = new HashMap<String, Object>();
		private String state ;
		
		
		public TreeDTO(String id, String text, String iconCls, int checked,
				String parentId, Map<String, Object> attributes, String state, String text2,String text3) {
			super();
			this.id = id;
			this.text = text;
			this.iconCls = iconCls;
			this.checked = checked;
			this.text2=text2;
			this.text3=text3;
			pid = parentId;
			this.attributes = attributes;
			this.state = state;
		}
		public TreeDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getText3() {
			return text3;
		}
		public void setText3(String text3) {
			this.text3 = text3;
		}
		public String getIconCls() {
			return iconCls;
		}
		public void setIconCls(String iconCls) {
			this.iconCls = iconCls;
		}
		public int getChecked() {
			return checked;
		}
		public void setChecked(int checked) {
			this.checked = checked;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String parentId) {
		    pid = parentId;
		}
		public Map<String, Object> getAttributes() {
			return attributes;
		}
		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}
		public String getText2() {
			return text2;
		}
		public void setText2(String text2) {
			this.text2 = text2;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		
}
