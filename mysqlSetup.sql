-- OOP 2022

DROP DATABASE IF EXISTS `music`;
CREATE DATABASE `music`;
USE `music`;
DROP TABLE IF EXISTS `artist`;
 CREATE TABLE artist (
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(50) NOT NULL,
     country VARCHAR(50),
     genre VARCHAR(20),
     active_since INT,
     biography TEXT,
     rating FLOAT(2,1)
 );

 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES ('The Beatles', 'United Kingdom', 'Rock', 1960, 'The Beatles were an English rock band formed in Liverpool in 1960. With a line-up comprising John Lennon, Paul McCartney, George Harrison and Ringo Starr, they are often regarded as the most influential band of all time. They were integral to the development of 1960s counterculture and popular music\'s recognition as an art form.', 4.5);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES       ('Michael Jackson', 'United States', 'Pop', 1964, 'Michael Jackson was an American singer, songwriter, and dancer. Dubbed the "King of Pop", he is regarded as one of the most significant cultural figures of the 20th century and one of the greatest entertainers in the history of music.', 4.8);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('Madonna', 'United States', 'Pop', 1982, 'Madonna Louise Ciccone is an American singer, songwriter, and actress. Referred to as the "Queen of Pop", she is regarded as one of the most influential figures in popular culture.', 4.2);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES       ('Bob Marley', 'Jamaica', 'Reggae', 1962, 'Bob Marley was a Jamaican singer, songwriter, and musician. Considered one of the pioneers of reggae, his musical style was characterized by his distinctive vocals, guitar playing, and songwriting skills. He is regarded as a global symbol of Jamaican culture and identity.', 4.6);
  INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES      ('Miles Davis', 'United States', 'Jazz', 1944, 'Miles Davis was an American jazz trumpeter, bandleader, and composer. He is widely considered one of the most influential musicians of the 20th century and a key figure in the development of jazz.', 4.3);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('Jimi Hendrix', 'United States', 'Rock', 1963, 'Jimi Hendrix was an American musician, singer, and songwriter. He is widely regarded as one of the most influential guitarists in the history of popular music, and one of the most celebrated musicians of the 20th century.', 4.7);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('Beyoncé', 'United States', 'R&B', 1990, 'Beyoncé Knowles-Carter is an American singer, songwriter, actress, and producer. She is regarded as one of the most influential and successful musicians in the contemporary music industry.', 4.5);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('Elvis Presley', 'United States', 'Rock', 1954, 'Elvis Presley was an American singer, musician, and actor. He is regarded as one of the most significant cultural icons of the 20th century and is often referred to as the "King of Rock and Roll".', 4.1);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('David Bowie', 'United Kingdom', 'Rock', 1962, 'David Bowie was an English singer, songwriter, and actor. He is regarded as one of the most influential musicians of the 20th century and is known for his distinctive voice and the intellectual depth of his work.', 4.4);
 INSERT INTO artist (name, country, genre, active_since, biography, rating)
 VALUES        ('Prince', 'United States', 'Pop', 1975, 'Prince was an American singer, songwriter, musician, record producer, and filmmaker. He was widely regarded as one of the greatest musicians of his generation, and his innovative work and flamboyant stage presence earned him critical acclaim and worldwide fame. His music blended a wide range of genres, including funk, rock, pop, and R&B, and his influence on popular culture is still felt today. Prince passed away in 2016 at the age of 57.', 4.5);