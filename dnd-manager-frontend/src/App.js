import React from 'react';
import CharacterList from './components/CharacterList';
import CharacterDetails from './components/CharacterDetails';

function App() {
    return (
        <div className="container mx-auto p-4">
            <h1 className="text-2xl font-bold mb-6">DnD Character Manager</h1>
            <div className="grid grid-cols-2 gap-4">
                <CharacterList />
                <CharacterDetails />
            </div>
        </div>
    );
}

export default App;
