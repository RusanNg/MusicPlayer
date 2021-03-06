package exp.rusan.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.bean.Track;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/8
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class TrackStore implements ITrackStoreModel {

    private static TrackStore trackStore;

    private static List<Track> tracks;
    private static List<Artist> artists;
    private static List<Album> albums;

    private TrackStore() {

        tracks = new ArrayList<>();
        artists = new ArrayList<>();
        albums = new ArrayList<>();

    }

    public static TrackStore getInstance() {

        if (trackStore == null) {
            synchronized (TrackStore.class) {
                if (trackStore == null) {
                    trackStore = new TrackStore();
                }
            }
        }

        return trackStore;

    }



    private List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> pTracks) {
        tracks = pTracks;
    }

    private List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> pArtists) {
        artists = pArtists;
    }

    private List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> pAlbums) {
        albums = pAlbums;
    }

    public void addTrack (Track pTrack) {
        tracks.add(pTrack);
    }

    public void addAlbum (Album pAlbum) {
        albums.add(pAlbum);
    }

    public void addArtist(Artist pArtist) {
        artists.add(pArtist);
    }

    public Track getTrackById(int pId) {
        Track track = null;
        for (Track t : tracks) {
            if (t.getId() == pId) {
                track = t;
                break;
            }
        }
        return track;

    }

    public Artist getArtistById(int pId) {

        Artist artist = null;
        for (Artist a : artists) {
            if (a.getId() == pId) {
                artist = a;
                break;
            }
        }

        return artist;
    }

    public Album getAlbumById(int pId) {
        Album album = null;
        for (Album a : albums) {
            if (a.getId() == pId) {
                album = a;
                break;
            }
        }
        return album;
    }

    public Album getAlbumByArtistId(int pId) {
        Album album = null;
        for (Album a : albums) {
            if (a.getArtistId() == pId) {
                album = a;
                break;
            }
        }

        if (album == null) {
            album = new Album.Builder(0, "Unknown").artistId(pId).artUri(null).build();
        }

        return album;
    }

    private void getDataResults(Object pData, LoadDataCallback pCallback) {
        if (pData != null) {
            pCallback.onDataLoaded(pData);
        } else {
            pCallback.onDataNotAvailable();
        }
    }

    @Override
    public void getTracks(LoadDataCallback pCallback) {
        getDataResults(tracks, pCallback);
    }

    @Override
    public void getArtists(LoadDataCallback pCallback) {
        getDataResults(artists, pCallback);
    }

    @Override
    public void getAlbums(LoadDataCallback pCallback) {
        getDataResults(albums, pCallback);
    }

    @Override
    public void getArtistById(int pId, LoadDataCallback pCallback) {

        getDataResults(getArtistById(pId), pCallback);
    }

    @Override
    public void getAlbumById(int pId, LoadDataCallback pCallback) {
        getDataResults(getAlbumById(pId), pCallback);
    }

    @Override
    public void getTrackById(int pId, LoadDataCallback pCallback) {
        getDataResults(getTrackById(pId), pCallback);
    }

    @Override
    public void getAlbumsByArtistId(int pId, LoadDataCallback pCallback) {

        List<Album> as = new ArrayList<>();

        for (Album a : albums) {
            if (a.getArtistId() == pId) {
                as.add(a);
            }
        }


        getDataResults(as, pCallback);

    }

    @Override
    public void getTracksByAlbumId(int pId, LoadDataCallback pCallback) {

        List<Track> ts = new ArrayList<>();

        for (Track t : tracks) {
            if (t.getAlbumId() == pId) {
                ts.add(t);
            }
        }

        getDataResults(ts, pCallback);

    }



    public void emptyTracks() {
        empryList(tracks);
    }

    public void emptyArtists() {
        empryList(artists);
    }

    public void emptyAlbums() {
        empryList(albums);
    }

    public void empryList(List pList) {
        pList.clear();
    }

}
