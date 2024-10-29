import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { selectCharacter } from '../features/characters/characterSlice';

const CharacterList = () => {
    const characters = useSelector((state) => state.characters.characters);
    const dispatch = useDispatch();

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Character List</h2>
            <ul className="space-y-2">
                {characters.map((char, index) => (
                    <li
                        key={index}
                        className="p-2 border rounded cursor-pointer hover:bg-gray-100"
                        onClick={() => dispatch(selectCharacter(char))}
                    >
                        {char.name}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CharacterList;
