package pgdp;

public class SpotiJy {
       private Artist[] artists;
    public SpotiJy(){
        this.artists = new Artist[0];
    }
    public boolean contains(Artist[] artists, Artist artist, int indexOfPointer){
        boolean contains = false;
        for (int i = 0; i <= indexOfPointer; i++) {
            if(artist.isEqual(artists[i])){
                contains = true;
                break;
            }
        }
        return contains;
    }

    public void addArtists(Artist[] artists) {
        int numberOfArtistsAdded = 0;
        if(this.artists != null) {
            if (this.artists.length == 0) {
                int count = 0; // to count duplicate elements
                int k = 0, m = 0, p = 0; // I'm using these variables to save duplicate element's indexes in an integer array.

                // number of duplicates
                for (int i = 0; i < artists.length; i++) {
                    for (int j = i + 1; j < artists.length; j++) {
                        if (artists[i].isEqual(artists[j])) {
                            count++;
                            break;
                        }
                    }
                }
                // count = 0 means I have no duplicates
                if (count == 0) {
                    this.artists = new Artist[artists.length];
                    for (int i = 0; i < artists.length; i++) {
                        this.artists[i] = artists[i];
                         numberOfArtistsAdded++;
                    }
                } else {
                    int[] arrayOfDuplicates = new int[count]; // to count on which index we have duplicates;
                    for (int i = 0; i < artists.length; i++) {
                        for (int j = i + 1; j < artists.length; j++) {
                            if ((artists[i].isEqual(artists[j]))) {
                                arrayOfDuplicates[k] = j;
                                k++;
                                break;
                            }
                        }
                    }
                    this.artists = new Artist[artists.length - count];
                    int count1 = 0;
                    for (int i = 0; i < artists.length; i++) {
                        for (int j = 0; j < arrayOfDuplicates.length; j++) {
                            if (i != arrayOfDuplicates[j]) {
                                count1++;
                            } else
                                break;

                        }
                        if (count1 == arrayOfDuplicates.length) {
                            this.artists[m] = artists[i];
                            m++;
                            numberOfArtistsAdded++;
                        }
                        count1 = 0;
                    }
                }


            } else {
                int k = 0;
                for (int i = 0; i < artists.length; i++) {
                    if (!contains(this.artists, artists[i], this.artists.length - 1))       // to determine how many elements I have to add to new array.
                        k++;
                }
                Artist[] tempArray = new Artist[this.artists.length + k];
                int pointerIndex = this.artists.length - 1;
                System.arraycopy(this.artists, 0, tempArray, 0, this.artists.length);
                for (int i = 0; i < artists.length; i++) {
                    if (!contains(tempArray, artists[i], pointerIndex)) {
                        tempArray[pointerIndex + 1] = artists[i];
                        pointerIndex++;
                        numberOfArtistsAdded++;
                    }
                }
                int numberOfNullsAdded = k - numberOfArtistsAdded;
                Artist[] newArray = new Artist[tempArray.length - numberOfNullsAdded];
                for (int i = 0; i < newArray.length; i++) {
                    newArray[i] = tempArray[i];
                }
                this.artists = newArray;
            }
        }
    }
    public String [] getTopTrendingArtist(){
        String[] topTrendingArtist = new String[2];
        int maxLikes = 0;
        for (int i = 0; i < artists.length; i++) {
            int temp = artists[i].totalLikes();
            if(temp > maxLikes){
                maxLikes = temp;
                topTrendingArtist[0] = artists[i].getFirstName();
                topTrendingArtist[1] = artists[i].getLastName();

            }
        }
        return topTrendingArtist;
    }
    public String getTopTrendingAlbum(){
        String topTrendingAlbum = null;
        int maxTotalLikes = 0;
        for (int i = 0; i < artists.length; i++) {
            for (int j = 0; j < artists[i].getAlbums().length; j++) {
                int temp = artists[i].getAlbums()[j].totalLikesOfAlbum();
                if(temp > maxTotalLikes){
                    maxTotalLikes = temp;
                    topTrendingAlbum = artists[i].getAlbums()[j].getTitle();
                }
            }
        }
        return topTrendingAlbum;
    }
    public String getTopTrendingSong(){
        String topTrendingSong = null;
        int maxLikes = 0;
        for (int i = 0; i < artists.length; i++) {
            for (int j = 0; j < artists[i].getAlbums().length; j++) {
                for (int k = 0; k < artists[i].getAlbums()[j].getSongs().length; k++) {
                    int temp = artists[i].getAlbums()[j].getSongs()[k].getLikes();
                   if(temp > maxLikes){
                       maxLikes = temp;
                       topTrendingSong = artists[i].getAlbums()[j].getSongs()[k].getTitle();
                   }
                }
            }
        }
        return topTrendingSong;
    }




    public Artist[] getArtists(){
        return artists;
    }


}
