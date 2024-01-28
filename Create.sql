CREATE DATABASE IF NOT EXISTS Library;

USE Library;

CREATE TABLE IF NOT EXISTS Author (
                                       AuthorID INT PRIMARY KEY,
                                       AuthorName VARCHAR(255) NOT NULL,
    BirthYear INT,
    Nationality VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS Genre (
                                      GenreID INT PRIMARY KEY,
                                      GenreName VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Publisher (
                                          PublisherID INT PRIMARY KEY,
                                          PublisherName VARCHAR(255) NOT NULL,
    EstablishedYear INT,
    HQLocation VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS Member (
                                       MemberID INT PRIMARY KEY,
                                       MemberName VARCHAR(255) NOT NULL,
    BirthYear INT,
    Address VARCHAR(255),
    Email VARCHAR(255) UNIQUE
    );

CREATE TABLE IF NOT EXISTS Book (
                                     BookID INT PRIMARY KEY,
                                     Title VARCHAR(255) NOT NULL,
    AuthorID INT,
    GenreID INT,
    PublisherID INT,
    PublicationYear INT,
    ISBN VARCHAR(13) NOT NULL,
    AvailableCopies INT,
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
    FOREIGN KEY (GenreID) REFERENCES Genres(GenreID),
    FOREIGN KEY (PublisherID) REFERENCES Publishers(PublisherID)
    );
