package net.drusantia.raidr.data

import com.google.gson.Gson
import net.drusantia.raidr.data.network.model.character.Character
import org.junit.Test

class CharacterTest {
    @Test(expected = Test.None::class) // no exception expected
    fun `parse character JSON to object`() {
        val character = Gson().fromJson(MockCharacterJSON, Character::class.java)
    }
}