package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.StudentModel;

@Mapper
public interface StudentMapper
{
    @Select("select * from mahasiswa where npm = #{npm}")
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, nama, tahun_masuk from mahasiswa")
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir,jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir},#{jenis_kelamin}, #{agama}, #{golongan_darah},#{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void addStudent (StudentModel student);
    
    @Delete("DELETE FROM student WHERE npm = #{npm}")
    void deleteStudent (@Param("npm") String npm);
    
    @Update("UPDATE mahasiswa SET nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir},jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah}, status = #{status}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} WHERE npm = #{npm}")
    void updateStudent (StudentModel student);
    
}
