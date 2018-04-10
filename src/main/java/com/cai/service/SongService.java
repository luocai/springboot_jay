package com.cai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.dao.SongMapper;
import com.cai.pojo.PageBean;
import com.cai.pojo.Song;


@Service
public class SongService{
	
	@Autowired
	private SongMapper songMapper;

	public void add(Song s) throws Exception{
		songMapper.add(s);
	}

	
	public void delete(int id) throws Exception{
		songMapper.delete(id);
	}

	public void update(Song s) throws Exception{

		songMapper.update(s);

	}

	public Song queryById(int id) throws Exception{
		Song s = songMapper.queryById(id);
		return s;
	}

	public List<Song> queryAll() throws Exception{
		List<Song> list = songMapper.queryAll();
//System.out.println("");
//System.out.println("queryALL");
		return list;
	}

	public List<Song> queryByPage(PageBean pb) throws Exception{
		
		return songMapper.queryByPage(pb);
	}


	public List<Song> findByName(String name) throws Exception{
		
		return songMapper.queryByName(name);
	}
		

}
