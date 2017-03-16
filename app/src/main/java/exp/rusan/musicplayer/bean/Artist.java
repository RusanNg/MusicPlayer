package exp.rusan.musicplayer.bean;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/28
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class Artist {

    private int id;
    private String title;
    private int numTracks;

    private int numAlbums;
    private String artUri;

    public static class Builder {

        private final int id;
        private final String title;

        private int numTracks;
        private int numAlbums = 0;
        private String artUri = null;

        public Builder(int pId, String pTitle) {
            this.id = pId;
            this.title = pTitle;
        }

        public Builder numTracks(int pNum) {
            numTracks = pNum;
            return this;
        }

        public Builder numAlbums(int pNum) {
            numAlbums = pNum;
            return this;
        }

        public Builder artUri(String pUri) {
            artUri = pUri;
            return this;
        }

        public Artist build() {
            return new Artist(this);
        }

    }

    private Artist(Builder pBuilder) {
        id = pBuilder.id;
        title = pBuilder.title;
        numTracks = pBuilder.numTracks;
        numAlbums = pBuilder.numAlbums;
        artUri = pBuilder.artUri;
    }

    public void setArtUri(String pArtUri) {
        artUri = pArtUri;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumTracks() {
        return numTracks;
    }

    public int getNumAlbums() {
        return numAlbums;
    }

    public String getArtUri() {
        return artUri;
    }
}
