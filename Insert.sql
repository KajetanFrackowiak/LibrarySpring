INSERT INTO author (author_name, birth_year, nationality)
VALUES ('John Doe', 1980, 'American'),
       ('Jane Smith', 1975, 'British'),
       ('Sam Johnson', 1990, 'Canadian');


INSERT INTO genre (genre_name)
VALUES ('Fiction'),
       ('Mystery'),
       ('Science Fiction');


INSERT INTO publisher (publisher_name, established_year, hq_location)
VALUES ('ABC Publications', 2000, 'New York'),
       ('XYZ Books', 1995, 'London'),
       ('123 Press', 2010, 'Toronto');


INSERT INTO book (title, publication_year, isbn, available_copies)
VALUES ('The Great Novel', 2015, '123456789', 100),
       ('Mystery at Midnight', 2018, '987654321', 75),
       ('Space Odyssey', 2020, '111222333', 50);


INSERT INTO member (member_name, birth_year, address, email)
VALUES ('Alice Johnson', 1985, '123 Main St', 'alice@example.com'),
       ('Bob Smith', 1992, '456 Oak St', 'bob@example.com');
