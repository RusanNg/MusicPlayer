package exp.rusan.musicplayer.TrackStore;

/**
 * Description: Album bean.
 * <!--
 * Author: Rusan
 * Date: 2017/2/13
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class Album {

    private Integer id;
    private String title;
    private String artist;
    private Integer trackNum;
    private String artUri;

    public Album(Integer pId, String pTitle, String pArtist, Integer pTrackNum, String pArtUri) {
        id = pId;
        title = pTitle;
        artist = pArtist;
        trackNum = pTrackNum;
        artUri = pArtUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer pId) {
        id = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String pArtist) {
        artist = pArtist;
    }

    public Integer getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(Integer pTrackNum) {
        trackNum = pTrackNum;
    }

    public String getArtUri() {
        return artUri;
    }

    public void setArtUri(String pArtUri) {
        artUri = pArtUri;
    }
}
