CREATE TABLE shared_event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    shared_with_user_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
    FOREIGN KEY (shared_with_user_id) REFERENCES user(id) ON DELETE CASCADE
);