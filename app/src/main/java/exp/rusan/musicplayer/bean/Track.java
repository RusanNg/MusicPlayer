package exp.rusan.musicplayer.bean;

/**
 * Description: Track bean.
 * <!--
 * Author: Rusan
 * Date: 2017/1/3
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class Track {

    private int id;
    private String title;
    private int duration;
    private String dataUri;

    private int artistId;
    private int albumId;
    private String artistTitle;
    private String albumTitle;

    public static class Builder {

        private final int id;
        private final String title;

        private int duration;
        private String dataUri;
        private int artistId = 0;
        private int albumId = 0;
        private String artistTitle = "Unknown";
        private String albumTitle = "Unknown";

        public Builder(int pId, String pTitle) {
            this.id = pId;
            this.title = pTitle;
        }

        public Builder duration(int pDuration) {
            duration = pDuration;
            return this;
        }

        public Builder dataUri(String pUri) {
            dataUri = pUri;
            return this;
        }

        public Builder artistId(int pId) {
            artistId = pId;
            return this;
        }

        public Builder albumId(int pId) {
            albumId = pId;
            return this;
        }

        public Builder artistTitle(String pTitle) {
            artistTitle = pTitle;
            return this;
        }

        public Builder albumTitle(String pTitle) {
            albumTitle = pTitle;
            return this;
        }

        public Track build() {
            return new Track(this);
        }

    }

    private Track(Builder pBuilder) {
        this.id = pBuilder.id;
        this.title = pBuilder.title;
        this.duration = pBuilder.duration;
        this.dataUri = pBuilder.dataUri;
        this.artistId = pBuilder.artistId;
        this.albumId = pBuilder.albumId;
        this.artistTitle = pBuilder.artistTitle;
        this.albumTitle = pBuilder.albumTitle;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getDataUri() {
        return dataUri;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }
}
