import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    characters: [
        { name: 'Aragorn', class: 'Ranger', level: 5, race: 'Human' },
        { name: 'Legolas', class: 'Archer', level: 4, race: 'Elf' },
        { name: 'Gimli', class: 'Warrior', level: 3, race: 'Dwarf' }
    ],
    selectedCharacter: null
};

export const characterSlice = createSlice({
    name: 'characters',
    initialState,
    reducers: {
        selectCharacter: (state, action) => {
            state.selectedCharacter = action.payload;
        },
        addCharacter: (state, action) => {
            state.characters.push(action.payload);
        },
        removeCharacter: (state, action) => {
            state.characters = state.characters.filter(
                (char) => char.name !== action.payload
            );
        }
    }
});

export const { selectCharacter, addCharacter, removeCharacter } = characterSlice.actions;

export default characterSlice.reducer;
