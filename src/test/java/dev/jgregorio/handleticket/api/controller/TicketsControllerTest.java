package dev.jgregorio.handleticket.api.controller;

import dev.jgregorio.handleticket.api.ticket.model.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void givenImage_whenParse_thenContainsJuan() throws Exception {
        String imagePath = "2020-11-07_fruteria.jpg";
        ClassPathResource resource = new ClassPathResource(imagePath, Grid.class);
        FileInputStream fis = new FileInputStream(resource.getFile());
        MockMultipartFile image = new MockMultipartFile(
                "file", "fruteria.jpg", "image/plain", fis);

        this.mockMvc.perform(multipart("/api/tickets")
                .file(image)
                .param("yErrorMax", "60"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[[\"Juan\"]]"));
    }
}