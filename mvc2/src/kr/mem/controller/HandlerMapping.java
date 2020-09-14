package kr.mem.controller;

import java.util.HashMap;

import kr.mem.pojo.*;

public class HandlerMapping {
	// Key, Value
	private HashMap<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		initMap();
	}
	// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�
	private void initMap() {
		// /list.do ---> MemberListController
		// /insert.do--> MemberInsertController
		// /insertForm.do--> MemberInsertFormController
		// /delete.do--> MemberDeleteController
		try {
		mappings.put("/list.do", new MemberListController());
		mappings.put("/insert.do", new MemberInsertController());
		mappings.put("/insertForm.do", new MemberInsertFormController());
		mappings.put("/delete.do", new MemberDeleteController());
		mappings.put("/content.do", new MemberContentController());
		mappings.put("/update.do", new MemberUpdateController());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	   // �� key�� �ش��ϴ� Controller�� ������
	   public Controller getController(String key) {
	      return mappings.get(key);
	   }
}
