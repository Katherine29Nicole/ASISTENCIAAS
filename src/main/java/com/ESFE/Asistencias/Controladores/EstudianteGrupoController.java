package com.ESFE.Asistencias.Controladores;


import com.ESFE.Asistencias.Servicios.Interfaces.IDocenteGrupoServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteGrupoServices;
import org.springframework.ui.Model;
import com.ESFE.Asistencias.Entidades.Estudiante;
import com.ESFE.Asistencias.Entidades.EstudianteGrupo;
import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/EstudianteGrupos")
public class EstudianteGrupoController {

    @Autowired
    private IEstudianteGrupoServices estudianteGrupoServices;

    @Autowired
    private IEstudianteServices estudianteServices; // Servicio para Estudiante

    @Autowired
    private IGrupoServices grupoServices; // Servicio para Grupo

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<EstudianteGrupo> estudianteGrupos = estudianteGrupoServices.BuscarTodosPaginados(pageable);
        model.addAttribute("estudianteGrupos", estudianteGrupos);

        int totalPage = estudianteGrupos.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumber);
        }
        return "estudianteGrupo/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("estudianteGrupo", new EstudianteGrupo());

        // Cargar lista de estudiantes y grupos
        List<Estudiante> estudiantes = estudianteServices.ObtenerTodos(); // Método para obtener todos los estudiantes
        List<Grupo> grupos = grupoServices.ObtenerTodos(); // Método para obtener todos los grupos

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("grupos", grupos);

        return "estudianteGrupo/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute EstudianteGrupo estudianteGrupo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("estudianteGrupo", estudianteGrupo);
            return "estudianteGrupo/create";
        }

        boolean isEdit = (estudianteGrupo.getId() != null && estudianteGrupoServices.BuscarporId(estudianteGrupo.getId()).isPresent());
        estudianteGrupoServices.CreaOeditar(estudianteGrupo);

        if (isEdit) {
            attributes.addFlashAttribute("msg", "Editado correctamente");
        } else {
            attributes.addFlashAttribute("msg", "Creado correctamente");
        }

        return "redirect:/EstudianteGrupos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Optional<EstudianteGrupo> estudianteGrupoOpt = estudianteGrupoServices.BuscarporId(id);
        if (estudianteGrupoOpt.isPresent()) {
            model.addAttribute("estudianteGrupo", estudianteGrupoOpt.get());
            return "estudianteGrupo/details";
        } else {
            return "estudianteGrupo/not_found";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        // Obtener el EstudianteGrupo para editar
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarporId(id)
                .orElseThrow(() -> new IllegalArgumentException("EstudianteGrupo no encontrado: " + id));

        // Obtener la lista de estudiantes y grupos
        List<Estudiante> estudiantes = estudianteServices.ObtenerTodos();
        List<Grupo> grupos = grupoServices.ObtenerTodos();

        // Añadir atributos al modelo
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("grupos", grupos);

        // Retornar la vista para editar
        return "estudianteGrupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarporId(id)
                .orElseThrow(() -> new IllegalArgumentException("EstudianteGrupo no encontrado: " + id));
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute EstudianteGrupo estudianteGrupo, RedirectAttributes attributes) {
        estudianteGrupoServices.EliminarPorId(estudianteGrupo.getId());
        attributes.addFlashAttribute("msg", "Eliminado correctamente");
        return "redirect:/EstudianteGrupos";
    }
}
