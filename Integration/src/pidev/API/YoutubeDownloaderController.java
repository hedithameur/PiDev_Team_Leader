/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author user
 */
public class YoutubeDownloaderController implements Initializable {
private static final String YOUTUBE_DL_COMMAND = "youtube-dl --extract-audio --audio-format mp3 ";
    @FXML
    private TextField textfield;
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private VBox root;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static final String API_KEY = "AIzaSyC4kTU9ydpPFxi2hvnmew72esl4N0wYdQM";

@FXML
private void Download(ActionEvent event) {
    String url = textfield.getText();
    if (url != null && !url.isEmpty()) {
        String videoId = getVideoIdFromUrl(url);
        if (videoId != null) {
            String videoUrl = getVideoUrlFromId(videoId);
            if (videoUrl != null) {
                downloadVideo(videoUrl);
            }
        }
    }
}

private String getVideoIdFromUrl(String url) {
    String videoId = null;
    if (url.startsWith("https://www.youtube.com/watch?v=")) {
        videoId = url.substring("https://www.youtube.com/watch?v=".length());
    }
    return videoId;
}

private String getVideoUrlFromId(String videoId) {
    String videoUrl = null;
    try {
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
        List<String> parts = new ArrayList<>();
parts.add("snippet");
        YouTube.Videos.List videosListByIdRequest = youtube.videos().list(parts);
        videosListByIdRequest.setKey(API_KEY);
        

        List<String> videoIds = new ArrayList<String>();
        videoIds.add(videoId);
        videosListByIdRequest.setId(videoIds);

        VideoListResponse response = videosListByIdRequest.execute();
        List<Video> videos = response.getItems();
        if (videos.size() > 0) {
            videoUrl = "https://www.youtube.com/watch?v=" + videoId;
        }
    } catch (GoogleJsonResponseException e) {
        System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
    } catch (IOException e) {
        System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
    } catch (Throwable t) {
        t.printStackTrace();
    }
    return videoUrl;
}


private void downloadVideo(String videoUrl) {
    try {
        URL url = new URL(videoUrl);
        InputStream inputStream = url.openStream();
        FileOutputStream outputStream = new FileOutputStream("video.mp4");

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        inputStream.close();
        outputStream.close();

        System.out.println("Téléchargement terminé avec succès");
        
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erreur lors du téléchargement");
    }
}

    @FXML
    private void retour(ActionEvent event) {
            try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../frontend/CreerPlaylist.fxml"));
            retour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
