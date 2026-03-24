-- Test-only seed data (H2-compatible)
-- Keeps integration tests (Postgres/Testcontainers) using src/main/resources/data.sql untouched.

INSERT INTO genres (id, name) VALUES (1, 'Action');
INSERT INTO genres (id, name) VALUES (2, 'Adventure');
INSERT INTO genres (id, name) VALUES (3, 'RPG');

INSERT INTO games (id, title, description, release_date, price, image_url, genre_id)
VALUES (
  '00000000-0000-0000-0000-000000000001',
  'Seed Test Game',
  'Seeded test data',
  DATE '2024-01-01',
  9.99,
  'https://example.com/seed-test-game.png',
  1
);
