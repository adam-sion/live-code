DROP SCHEMA IF EXISTS live_code CASCADE;
CREATE SCHEMA live_code;

DROP TABLE IF EXISTS live_code.users CASCADE;
CREATE TABLE live_code.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT email_format CHECK (email ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')
);


DROP TABLE IF EXISTS live_code.rooms CASCADE;
CREATE TABLE live_code.rooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS live_code.room_user CASCADE;
CREATE TABLE live_code.room_user (
    room_id BIGINT NOT NULL REFERENCES live_code.rooms(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES live_code.users(id) ON DELETE CASCADE,
    role VARCHAR(50) CHECK(role IN ('user', 'admin')) DEFAULT 'user',
    last_active_at TIMESTAMP,
    PRIMARY KEY (room_id, user_id)
);

DROP TABLE IF EXISTS live_code.room_user_requests CASCADE;
CREATE TABLE live_code.room_user_requests (
    id SERIAL PRIMARY KEY,
    room_id BIGINT NOT NULL REFERENCES live_code.rooms(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES live_code.users(id) ON DELETE CASCADE,
    status VARCHAR(20) CHECK(status IN ('pending', 'accepted', 'declined')) DEFAULT 'pending'
);


DROP TABLE IF EXISTS live_code.room_code CASCADE;
CREATE TABLE live_code.room_code (
    id SERIAL PRIMARY KEY,
    room_id BIGINT NOT NULL REFERENCES live_code.rooms(id) ON DELETE CASCADE,
    language VARCHAR(50) NOT NULL,
    content TEXT NOT NULL
);