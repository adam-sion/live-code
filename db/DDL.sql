
DROP SCHEMA IF EXISTS live_code CASCADE;
CREATE SCHEMA live_code;


DROP TABLE IF EXISTS live_code.users CASCADE;
CREATE TABLE live_code.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
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
    PRIMARY KEY (room_id, user_id)
);


DROP TABLE IF EXISTS live_code.active_room_user CASCADE;
CREATE TABLE live_code.active_room_user (
    room_id BIGINT NOT NULL REFERENCES live_code.rooms(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES live_code.users(id) ON DELETE CASCADE,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
    language VARCHAR(50) NOT NULL
);


DROP TABLE IF EXISTS live_code.code_line_details CASCADE;
CREATE TABLE live_code.code_line_details (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    line_number INT NOT NULL
);

-- Create the 'code_line' table to store lines of code linked to room_code and users
DROP TABLE IF EXISTS live_code.code_line CASCADE;
CREATE TABLE live_code.code_line (
    id SERIAL PRIMARY KEY,
    room_code_id BIGINT NOT NULL REFERENCES live_code.room_code(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES live_code.users(id) ON DELETE CASCADE,
    code_line_details_id BIGINT NOT NULL REFERENCES live_code.code_line_details(id) ON DELETE CASCADE,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Function to update the 'updated_at' field when a code line is updated
CREATE OR REPLACE FUNCTION set_updated_at() 
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;  -- Set updated_at to current timestamp
    RETURN NEW;  -- Return the modified row
END;
$$ LANGUAGE plpgsql;

-- Trigger to update the 'updated_at' field in 'code_line' table when a row is updated
CREATE TRIGGER update_code_line_timestamp
BEFORE UPDATE ON live_code.code_line
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
