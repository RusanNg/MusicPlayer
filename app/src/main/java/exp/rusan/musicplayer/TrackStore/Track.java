package exp.rusan.musicplayer.TrackStore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description: Song bean.
 * <!--
 * Author: Rusan
 * Date: 2017/1/3
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

@Entity
public class Track {

    @Id
    private Integer id;
    private String title;
    private String artist;
    private String album;
    private Integer duration;

    @Generated(hash = 1686194749)
    public Track(Integer id, String title, String artist, String album, Integer duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    @Generated(hash = 1672506944)
    public Track() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}