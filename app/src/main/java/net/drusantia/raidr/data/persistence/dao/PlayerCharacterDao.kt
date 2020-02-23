package net.drusantia.raidr.data.persistence.dao
////
////import androidx.lifecycle.LiveData
////import androidx.room.*
////import androidx.room.OnConflictStrategy.REPLACE
////import net.drusantia.raidr.data.model.PlayerCharacter
////
////@Dao
////interface PlayerCharacterDao {
////    // [C]R[U]D
////    @Insert(onConflict = REPLACE)
////    fun upsert(PlayerCharacter: PlayerCharacter)
////
////    @Insert
////    fun insertAll(vararg playerCharacters: PlayerCharacter)
////
////    // C[R]UD
////    @Query("SELECT * FROM PlayerCharacter WHERE path=:path")
////    fun getByPath(path: String): LiveData<PlayerCharacter>
////
////    @Query("SELECT * FROM PlayerCharacter WHERE regionName=:region and realm=:realm")
////    fun getByRealm(region: String, realm: String): LiveData<List<PlayerCharacter>>
////
////    // CRU[D]
////    fun delete(PlayerCharacter: PlayerCharacter) = deleteById(PlayerCharacter.path)
////
////    @Query("DELETE FROM PlayerCharacter WHERE path=:path")
////    fun deleteById(path: String)
////
////    @Query("DELETE FROM PlayerCharacter")
////    fun clear()
////}