import React from 'react';
import { useSelector } from 'react-redux';

const CharacterDetails = () => {
    const selectedCharacter = useSelector((state) => state.characters.selectedCharacter);

    if (!selectedCharacter) {
        return <div className="p-4">Select a character to see the details.</div>;
    }

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Character Details</h2>
            <p><strong>Name:</strong> {selectedCharacter.name}</p>
            <p><strong>Class:</strong> {selectedCharacter.class}</p>
            <p><strong>Level:</strong> {selectedCharacter.level}</p>
            <p><strong>Race:</strong> {selectedCharacter.race}</p>
        </div>
    );
};

export default CharacterDetails;
