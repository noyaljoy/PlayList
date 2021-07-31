=======================================================================

Application URL: http://localhost:8055/

Swagger URL: http://localhost:8055//swagger-ui.html#/

H2 DB: http://localhost:8055/h2-console/
JDBC URL: jdbc:h2:mem:music
User Name: album
Password: songs

========================================================================

addSongToPlayList
----------------
http://localhost:8055/playListApi/addSongToPlayList?playListId=1&songId=5

removeSongFromPlayList
-----------------------
http://localhost:8055/removeSongFromPlayList?playListId=1&songId=2


PlayList API Details:
===========================
POST:: PlayList  URL: http://localhost:8055/playListApi/playList
{  
  "name": "playList2" 
} 

GET:: PlayList   URL: http://localhost:8055/playListApi/playList
GET:: PlayList   URL: http://localhost:8055/playListApi/playList/{id}
Delete:: PlayList   URL: http://localhost:8055/playListApi/playList/
Delete:: PlayList   URL: http://localhost:8055/playListApi/playList/{id}


Songs API Details:
=======================
POST:: Song  URL: http://localhost:8055/songApi/song
{
  "name": "song5",
  "singer": "singer5"
}

GET:: Songs   URL: http://localhost:8055/songApi/song
GET:: Song   URL: http://localhost:8055/songApi/song/{id}
Delete:: Songs   URL: http://localhost:8055/songApi/song
Delete:: Song   URL: http://localhost:8055/songApi/song{id}

=======================================================================


