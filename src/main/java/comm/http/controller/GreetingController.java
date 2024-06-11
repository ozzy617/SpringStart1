package comm.http.controller;

import comm.database.entity.Role;
import comm.database.repository.CompanyRepository;
import comm.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/api/v1")
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    //@RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public String hello(@RequestParam("age") Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id,
                              Model model,
                        UserReadDto userReadDto) {
//        mv.setViewName("greeting/hello");
//        model.addAttribute("user", new UserReadDto(1L, "Andrei"));
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public String bye(@SessionAttribute("user") UserReadDto user) {
        //mv.setViewName("greeting/bye");
        return "greeting/bye";
    }
}
