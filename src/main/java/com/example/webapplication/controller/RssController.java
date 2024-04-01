package com.example.webapplication.controller;

import com.example.webapplication.component.RSSFeed;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
@Log4j2
public class RssController {

    private final RSSFeed rssFeed;

    String MARKER_NAME = "RSS_CONTROLLER";
    private final Marker mk;

    public RssController (RSSFeed rssFeed) {
        this.rssFeed = rssFeed;
        mk = MarkerManager.getMarker(MARKER_NAME);
    }
    @GetMapping(path = "file/rss")
    public String getRssContentByFileName(@RequestParam(required = true) String fileName,
                                          @RequestParam(required = true) boolean largeFileMode) {
        if (largeFileMode) {
            //Todo: support large file content streaming, or download
            log.warn(mk, "Large file mode is currently not supported. Wait for a next update.");
        }
        return rssFeed.readFromFile(mk, fileName);
    }

    @GetMapping(path = "web/rss")
    public String getRssContentByWebName(@RequestParam(required = true) String urlPath,
                                         @RequestParam(required = true, defaultValue = "false") boolean isFullUrl) throws IOException {
        return rssFeed.getFromWebRss(mk, urlPath, isFullUrl);
    }
}
