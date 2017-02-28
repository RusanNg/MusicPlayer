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
    private String album;
    private String artist;
    private Integer numSongs;
    private String artUri;

    public Album(Integer pId, String pAlbum, String pArtist, Integer pNumSongs, String pArtUri) {
        id = pId;
        album = pAlbum;
        artist = pArtist;
        numSongs = pNumSongs;
        artUri = pArtUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer pId) {
        id = pId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String pAlbum) {
        album = pAlbum;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String pArtist) {
        artist = pArtist;
    }

    public Integer getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(Integer pNumSongs) {
        numSongs = pNumSongs;
    }

    public String getArtUri() {
        return artUri;
    }

    public void setArtUri(String pArtUri) {
        artUri = pArtUri;
    }
}
