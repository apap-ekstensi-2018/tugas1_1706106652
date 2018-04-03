package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
    		@RequestParam(value = "npm", required = false) String npm,
    		@RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
            @RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
            @RequestParam(value = "agama", required = false) String agama,
            @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
            @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
            @RequestParam(value = "id_prodi", required = false) String id_prodi)
    {
        StudentModel student = new StudentModel (npm,nama,tempat_lahir,tanggal_lahir,Integer.valueOf(jenis_kelamin),agama,golongan_darah,status,tahun_masuk,jalur_masuk,Integer.valueOf(id_prodi));
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
    	if(student == null) {
			return "not-found";
		}
        studentDAO.deleteStudent (npm);

        return "delete";
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
    	if(student == null) {
			return "not-found";
		}
    	model.addAttribute ("student", student);

        return "form-update";
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (StudentModel student)
    {
        
        studentDAO.updateStudent (student);

        return "success-update";
    }

}
