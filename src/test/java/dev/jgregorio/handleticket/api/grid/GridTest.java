package dev.jgregorio.handleticket.api.grid;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handleticket.api.ticket.GoogleVisionService;
import dev.jgregorio.handleticket.api.ticket.util.ResourceUtil;
import dev.jgregorio.handleticket.api.ticket.model.grid.Grid;

import java.io.IOException;
import java.util.List;

public class GridTest {

    //@Test
    public void parseImage2NormalizedTextGrid() throws IOException {
        String fileName = "2020-11-07_fruteria_2.jpg";
        //printImageText(fileName);
        String filePath = ResourceUtil.getRelativePath(Grid.class, fileName);
        List<EntityAnnotation> annotations = GoogleVisionService.getAnnotations(filePath);

        Grid grid = new Grid(25, "\n", ";");
        grid.addAnnotations(annotations);
        String textFormatted = grid.getTextFormatted();
        System.out.print(textFormatted);
    }
}
