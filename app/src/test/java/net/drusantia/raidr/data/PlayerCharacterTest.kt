package net.drusantia.raidr.data

import com.google.gson.Gson
import net.drusantia.raidr.data.model.PlayerCharacter
import org.junit.Test

class PlayerCharacterTest {
    @Test(expected = Test.None::class) // no exception expected
    fun `parse character JSON to object`() {
        val character = Gson().fromJson(MockCharacterJSON, PlayerCharacter::class.java)
    }
}