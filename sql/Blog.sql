DROP TRIGGER IF EXISTS tr_update_user ON "user";
DROP TRIGGER IF EXISTS tr_create_user ON "user";
DROP TRIGGER IF EXISTS tr_delete_profile ON profile;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS post_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS "user";

CREATE TABLE "user" (
                        user_id BIGSERIAL PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        dt_created TIMESTAMP DEFAULT now() NOT NULL,
                        dt_updated TIMESTAMP,
                        is_active BOOLEAN
);

CREATE TABLE role (
                      role_id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL
);

CREATE TABLE user_role (
                           user_id BIGINT REFERENCES "user" (user_id),
                           role_id BIGINT REFERENCES role(role_id),
                           PRIMARY KEY (user_id, role_id)
);


CREATE TABLE post (
                      post_id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      content VARCHAR,
                      user_id BIGINT REFERENCES "user"(user_id),
                      dt_created TIMESTAMP DEFAULT now() NOT NULL,
                      dt_updated TIMESTAMP
);

CREATE TABLE tag (
                     tag_id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(50) NOT NULL
);

CREATE TABLE post_tag (
                          post_id BIGINT REFERENCES post(post_id) ON DELETE CASCADE,
                          tag_id BIGINT REFERENCES tag(tag_id) ON DELETE CASCADE,
                          PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE comment (
                         comment_id BIGSERIAL PRIMARY KEY,
                         post_id BIGINT REFERENCES post(post_id) ON DELETE CASCADE,
                         content VARCHAR,
                         user_id BIGINT REFERENCES "user"(user_id),
                         dt_created TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE profile (
                         profile_id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT REFERENCES "user"(user_id),
                         username varchar(255),
                         name VARCHAR(255),
                         last_name VARCHAR(255),
                         photo_path VARCHAR(255),
                         about VARCHAR

);


-- triggers


--
--
--
-- CREATE OR REPLACE FUNCTION new_profile() RETURNS TRIGGER
-- AS $$
-- BEGIN
-- INSERT INTO profile(username) VALUES (NEW.username);
-- RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER tr_new_profile
--     AFTER INSERT ON "user"
--     FOR EACH ROW EXECUTE PROCEDURE new_profile();
--
--
--
--
-- CREATE OR REPLACE FUNCTION update_user() RETURNS TRIGGER
-- AS $$
-- BEGIN
-- UPDATE "user" SET dt_updated = now() WHERE user_id=OLD.user_id;
-- RETURN OLD;
-- END;
-- $$ LANGUAGE plpgsql;
--
--
--
--
-- CREATE TRIGGER tr_update_user
--     AFTER UPDATE ON profile
--     FOR EACH ROW EXECUTE PROCEDURE update_user();
--
--
--
--
--
-- CREATE OR REPLACE FUNCTION delete_profile() RETURNS TRIGGER
-- AS $$
-- BEGIN
-- DELETE FROM profile WHERE username=OLD.username;
-- RETURN OLD;
-- END;
-- $$ LANGUAGE plpgsql;
--
--
--
-- CREATE TRIGGER tr_delete_profile
--     BEFORE DELETE ON "user"
--     FOR EACH ROW EXECUTE PROCEDURE delete_profile();
--

-- Insert

insert into role(name) values ('ADMIN');
insert into role(name) values ('USER');


insert into "user" (username, password, is_active)
values ('masha010', '$2a$10$DBkNVIqXza47hfVFRPE2zOhNrC39sIu6snxnX.l5djiDvozhRDzvq', true);

insert into "user" (username, password, is_active)
values ('ilya145', '$2a$10$7XEwVhurjxbNN5JawpuJe.ugVD4aoGdrRLKEHXBk2kb2s7CktQuuu', true);
insert into "user" (username, password, is_active)
values ('admin', '$2a$10$buoJH25TDz1uWyIKM/u2get0.VIifSX3oWEvh6JBDxhQh79YHC2Q6', true);
insert into "user" (username, password, is_active)
values ('igor_igor', '$2a$10$ARPZ3bsJsKl6waNbsKdIveeZ2LPFCwFod4USdmvwiKRGEOm3.gpMG', true);
insert into "user" (username, password, is_active)
values ('xhad_x', '$2a$10$WhWctMymGZgw.SL97AINO.G0h0CjeC63VG5BDwnlgboizxh7mtLm6', true);
insert into "user" (username, password, is_active)
values ('HuNtErX', '$2a$10$J8k4tFPIB9qJejBFVcyr2u0dFswhucQHn3wgA1HeGtUzQuUv0m8cC', true);
insert into "user" (username, password, is_active)
values ('alhelm_22', '$2a$10$OfVM2SDIjJ139PLIzIxe4e2p7XPQFRO6CMlrK6xL03/KDLtQfgbC.', true);
insert into "user" (username, password, is_active)
values ('provocator77', '$2a$10$ifFGEN98lFVmFgQeBQiJ8u1HSqykCRyFzgodVr6PyV5lkjb/UKVO6', true);
insert into "user" (username, password, is_active)
values ('mirea', '$2a$10$hw5OYPuSML6.fBsImgPQ9un18LKgWpaqAJcAxVuG6g41kKLVUqgrm', true);

insert into user_role values (3, 1);
insert into user_role values (1, 2);
insert into user_role values (2, 2);
insert into user_role values (8, 2);
insert into user_role values (4, 2);
insert into user_role values (5, 2);
insert into user_role values (6, 2);
insert into user_role values (7, 2);


insert into post (title, content, user_id, dt_created, dt_updated)
values ('
General characteristics of the coronavirus', 'A new coronavirus infection is a disease that affects the human respiratory system.', 1, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('
Where did the coronavirus come from?','The spread of the SARS-CoV-2 virus began from the Chinese city of Wuhan. Last year, there was a massive outbreak of the disease. According to the latest data, about 34 million cases of coronavirus infection have been identified in the world today.', 1, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Acceptance of applications for the national scientific and practical', 'In the period from April 19 to May 20, MIREA - the Russian Technological University will host a national scientific and practical conference "Digitalization of the technosphere: a scientific approach".
⠀
The conference is held to test scientific activities for training programs in master''s and postgraduate studies in enlarged groups of specialties (UGS), which can be represented by experts as part of the conference program committee.
⠀
During the conference, plenary sessions and sessions of sections are planned, which may reflect the topics of one or several UGS or scientific areas.The meetings will be held in face-to-face and remote format, it is possible to use face-to-face format with remote participation. Additionally, it is possible to present reports in the form of voiced presentations for posting on the Internet at the conference resources.
⠀
Information page of the conference, requirements for the formatting of articles, contacts of the organizers and the program committee on the website of RTU MIREA.', 9, current_timestamp, null);

insert into post (title, content, user_id, dt_created, dt_updated)
values ('RTU MIREA students met with a representative of the CJSC Department.', 'More than 50 students took part in a meeting with police lieutenant colonel Anastasia Myasnikova, an investigator of the drug control department of the Internal Affairs Directorate for the Western Administrative District of the Main Directorate of the Ministry of Internal Affairs of Russia for Moscow.
⠀
The meeting was dedicated to the prevention and suppression of the spread of narcotic drugs and psychotropic substances among the youth. The guys expressed their sincere readiness to assist law enforcement agencies in the fight against this dangerous phenomenon.
⠀
The participants of the meeting talked about the most famous ways of trafficking in illegal substances, including illegal sales from the "dark" segment of the Internet.Anastasia Myasnikova spoke about the plans of the Moscow police to build closer interaction with the advanced students, about the intention to create conditionally called cyber squads - youth squads that help to establish advertising sources of poison on the global network, their URL links, and bindings to specific persons involved.
⠀
Concluding the meeting, Pavel Shchurov, Deputy Head of the Department for Educational and Social Work of RTU MIREA, assured that the university''s assets will be represented by one of the first educational institutions of the capital, which will support and take part in a useful endeavor of law enforcement officers - the creation of cyber squads.', 9, current_timestamp , null);

insert into post (title, content, user_id, dt_created, dt_updated)
values ('Football without the Champions League ?! What?', 'The Champions League and Europa League may not be finished this season!', 5, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('S.T.A.L.K.E.R Birthday! ', ' Today Sergey Grigorovich is celebrating his birthday - it was he and his company that created STALKER, he is 43 years old! Thanks for the best childhood game!', 6, current_timestamp, null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Big money!', 'The first season of The Lord of the Rings will cost $ 500 million! For comparison: $ 50-60 million was spent on the first season of Game of Thrones. Thus, the series will become the most expensive in history.', 7, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Men''s tears', 'Exactly 21 years ago, the premiere of the film "The Green Mile" took place in the Russian film distribution.', 7, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Shanghai News', '
Meanwhile, in Shanghai, a huge QR code from a thousand drones was discovered over the city center! I wonder what his goal is?', 6, current_timestamp, null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Superheroes in your life!','A little superhero started up in Turkey: three dogs attacked the cat and drove it into a corner ... when suddenly the little fluffy hero burst into the showdown and drove the villains away from his brother!', 8, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('Monument day', '
Today, the whole world celebrates the International Day of Monuments and Distinguished Sites!', 8, current_timestamp , null);
insert into post (title, content, user_id, dt_created, dt_updated)
values ('The pride of the nation!', '
Russian schoolgirls won the European Mathematics Olympiad! A team of four Russian schoolgirls received all four gold medals and took first place in the overall standings.', 5, current_timestamp, null);

insert into comment (user_id, post_id, content, dt_created)
values (2, 2, 'Nice!', current_timestamp);
insert into comment (user_id, post_id, content, dt_created)
values (2, 1, 'Awesome!', current_timestamp);
insert into comment (user_id, post_id, content, dt_created)
values (2, 1, 'Excellent!', current_timestamp);
insert into comment (user_id, post_id, content, dt_created)
values (1, 1, 'Wonderful!', current_timestamp);
insert into comment (user_id, post_id, content, dt_created)
values (2, 3, 'Disgusting!', current_timestamp);
insert into comment (user_id, post_id, content, dt_created)
values (2, 3, 'Atrocious!', current_timestamp);

insert into tag (name) values ('news');
insert into tag (name) values ('life');
insert into tag (name) values ('day');
insert into tag (name) values ('mood');
insert into tag (name) values ('ideas');
insert into tag (name) values ('thoughts');

insert into post_tag(post_id, tag_id) values (1, 1);
insert into post_tag(post_id, tag_id) values (2, 1);
insert into post_tag(post_id, tag_id) values (3, 1);
insert into post_tag(post_id, tag_id) values (4, 1);
insert into post_tag(post_id, tag_id) values (5, 1);
insert into post_tag(post_id, tag_id) values (6, 1);
insert into post_tag(post_id, tag_id) values (7, 1);
insert into post_tag(post_id, tag_id) values (8, 1);
insert into post_tag(post_id, tag_id) values (9, 1);
insert into post_tag(post_id, tag_id) values (10, 1);
insert into post_tag(post_id, tag_id) values (1, 2);
insert into post_tag(post_id, tag_id) values (2, 3);
insert into post_tag(post_id, tag_id) values (2, 2);
insert into post_tag(post_id, tag_id) values (2, 6);
insert into post_tag(post_id, tag_id) values (2,5);
insert into post_tag(post_id, tag_id) values (3, 3);
insert into post_tag(post_id, tag_id) values (3, 2);
insert into post_tag(post_id, tag_id) values (3, 6);




-- SELECT username, password, dt_created, dt_updated, is_active, role_id, username, password, role_id
-- from "user" inner join user_role using (user_id);
--
-- UPDATE profile SET name = 'Masha', last_name = 'Gladysheva' where username = 'masha010';
--
--
--
-- DELETE from post WHERE user_id = 1;
-- DELETE from user_role WHERE user_id = 1;
-- DELETE from "user" WHERE user_id = 1;
--
--
-- CREATE VIEW largePosts AS
-- SELECT u.user_id, u.username, p.title, p.content from "user" u INNER JOIN post p ON u.user_id = p.user_id WHERE char_length(p.content)  > (SELECT AVG(char_length(content)) FROM post);
--
--
-- SELECT * FROM largePosts;