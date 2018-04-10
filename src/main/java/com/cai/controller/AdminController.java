package com.cai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cai.pojo.PageBean;
import com.cai.pojo.Song;
import com.cai.service.SongService;

@Controller

@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SongService songService;
	
	@RequestMapping("/queryAll")
	public ModelAndView quereyAll(PageBean pb)throws Exception{
		ModelAndView mlv = new ModelAndView();
		List<Song> l = songService.queryAll();
		int totalRecord = l.size();
		
		pb.setTotalRecord(totalRecord);
		pb.caculateSomeProperty();
		List<Song> list = songService.queryByPage(pb);
		boolean flag = true;
		
		mlv.addObject("flag", flag);
		mlv.addObject("list", list);
		mlv.addObject("page", pb);
		mlv.setViewName("admin/queryAll");
//System.out.println("已经进入controller");
		return mlv;
	}
	
	@RequestMapping("toAdd")
	public ModelAndView toAdd()throws Exception{
		return new ModelAndView("admin/add");
	}
	
	@RequestMapping("add")
	public ModelAndView add(Song s)throws Exception{
		
		songService.add(s);
		return new ModelAndView("redirect:/admin/queryAll");
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(Song s)throws Exception{
		songService.delete(s.getId());
//System.out.println("ɾ���ɹ���");
		return new ModelAndView("redirect:/admin/queryAll");
	}
	
	@RequestMapping("toEdit")
	public ModelAndView toEdit(Song s)throws Exception{
		Song song = songService.queryById(s.getId());
		ModelAndView mlv = new ModelAndView();
		mlv.addObject(song);
		mlv.setViewName("admin/edit");
		return mlv;
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(Song s)throws Exception{

		songService.update(s);

		return new ModelAndView("redirect:/admin/queryAll");
	}
	
	
	
	@RequestMapping(value = "findByName")
	public ModelAndView findByName(String name) throws Exception{
		
		List<Song> list = songService.findByName(name);
		
		ModelAndView mlv = new ModelAndView();
		
		mlv.addObject("list", list);
		mlv.setViewName("admin/queryAll");
		
		return mlv;
	}
	
}
