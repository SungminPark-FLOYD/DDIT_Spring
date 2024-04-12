package kr.or.ddit.adressBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.adressBook.service.AdressBookService;
import kr.or.ddit.vo.AdressBookVO;

@Controller
public class AddressBookController {
	@Autowired
	AdressBookService service;
	
	public List<AdressBookVO> addressList() {
		return service.selectAddressList();
	}
}
