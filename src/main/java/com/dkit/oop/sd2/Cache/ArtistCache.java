package com.dkit.oop.sd2.Cache;

import java.util.HashSet;

public class ArtistCache {
    private HashSet<Integer> artistIds;

    public ArtistCache(){
        this.artistIds=new HashSet<>();
    }
    public boolean contains(int artistId){
        return artistIds.contains(artistId);
    }
    public void add(int  artistId) {
        artistIds.add(artistId);
    }

    public void remove(int artistId) {
        artistIds.remove(artistId);
    }

}
