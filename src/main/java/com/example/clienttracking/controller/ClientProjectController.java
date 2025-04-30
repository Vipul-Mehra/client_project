    package com.example.clienttracking.controller;

    import com.example.clienttracking.model.ClientProjects;
    import com.example.clienttracking.service.ClientProjectService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/clientprojects")
    @CrossOrigin(origins = "*")
    public class ClientProjectController {

        @Autowired
        private ClientProjectService clientProjectService;

        @GetMapping
        public List<ClientProjects> getAllClientProject() {
            return clientProjectService.getAllClientProject();
        }

        @GetMapping("/{id}")
        public ClientProjects getClientProjectById(@PathVariable Long id) {
            return clientProjectService.getClientProjectById(id);
        }

        @PostMapping
        public ClientProjects createClientProject(@RequestBody ClientProjects clientProject) {
            return clientProjectService.createClientProject(clientProject);
        }

        @PutMapping("/{id}")
        public ClientProjects updateClientProject(@PathVariable Long id, @RequestBody ClientProjects clientProject) {
            return clientProjectService.updateClientProject(id, clientProject);
        }

        @DeleteMapping("/{id}")
        public void deleteClientProject(@PathVariable Long id) {
            clientProjectService.deleteClientProject(id);
        }
    }
