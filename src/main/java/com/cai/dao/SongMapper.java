package com.cai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Mapper;

import com.cai.pojo.PageBean;
import com.cai.pojo.Song;
@Mapper
public interface SongMapper {
	
	@Insert("insert into songs(name,album,time) values (#{name}, #{album}, #{time})")
	public void add(Song s)throws Exception;
	
	@Delete("delete from songs where id = #{id};")
	public void delete(int id)throws Exception;
	
	@Update("update songs set name = #{name}, album = #{album}, time = #{time} where id = #{id}")
	public void update(Song s)throws Exception;
	
	@Select("select *from songs")
	public List<Song> queryAll()throws Exception;
	
	@Select("select *from songs where id = #{id}")
	public Song queryById(int id)throws Exception;
	
	@Select("select *from songs limit #{startIndex},#{pageSize}")
	public List<Song> queryByPage(PageBean pb)throws Exception;
	
	@Select("select *from songs where name = #{name}")
	public List<Song> queryByName(String name)throws Exception;
}
