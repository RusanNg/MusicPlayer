package exp.rusan.musicplayer.bean;

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

    private int id;
    private String title;

    private int numTracks;
    private String artistTitle;
    private String artUri;

    private int artistId;

    public static class Builder {

        private final int id;
        private final String title;

        private int numTracks;
        private String artistTitle;
        private String artUri;

        private int artistId;

        public Builder(int pId, String pTitle) {
            this.id = pId;
            this.title = pTitle;
        }

        public Builder numTracks(int pNum) {
            numTracks = pNum;
            return this;
        }

        public Builder artistTitle(String pTitle) {
            artistTitle = pTitle;
            return this;
        }

        public Builder artUri(String pUri) {
            artUri =  pUri;
            return this;
        }

        public Builder artistId(int pId) {
            artistId = pId;
            return this;
        }

        public Album build() {
            return new Album(this);
        }

    }

    private Album(Builder pBuilder) {
        id = pBuilder.id;
        title = pBuilder.title;

        numTracks = pBuilder.numTracks;
        artistTitle = pBuilder.artistTitle;
        artUri = pBuilder.artUri;

        artistId = pBuilder.artistId;
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

    public String getArtistTitle() {
        return artistTitle;
    }

    public String getArtUri() {
        return artUri;
    }

    public int getArtistId() {
        return artistId;
    }
}
