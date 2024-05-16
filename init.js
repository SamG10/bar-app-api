require('dotenv').config();
const { Client } = require('pg');
const fs = require('fs');
const path = require('path');

async function executeScript() {
    const client = new Client({
        host: 'localhost',
        user: 'postgres',
        password: 'root',
        database: 'bar_app',
        port: '5432'
    });

    try {
        await client.connect();
        console.log('Connected to the database');

        const script = fs.readFileSync(path.join(__dirname, 'script-init.sql')).toString();
        await client.query(script);
        console.log('Script executed successfully');

    } catch (err) {
        console.error('Error executing script', err);
    } finally {
        await client.end();
        console.log('Disconnected from the database');
    }
}

executeScript();
