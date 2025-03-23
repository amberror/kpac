INSERT INTO kpac (title, description, creation_date)
VALUES
    ('How to make a nuclear reactor out of noodles and a piece of wood', 'It will blow your mind', '2024-12-19'),
    ('How to live this life Pack', 'Unbelievably incredible pack', '2025-03-18'),
    ('How to be born in rich family Pack', 'Make this life easier', '2018-05-30'),
    ('How to use microwave as entertainment Pack', 'Lets get unforgettable emotions', '2022-01-13'),
    ('How to train hamsters Pack', 'If you do not believe in your abilities, then believe in them.', '2021-08-10');

INSERT INTO kpac_set (title)
VALUES
    ('Mega Pack'),
    ('Ultra Pack'),
    ('Unbelievably huge Pack');

INSERT INTO kpac_set_kpac (kpac_set_id, kpac_id)
VALUES
    (1, 1), -- "Mega Pack" contains "How to make a nuclear reactor out of noodles and a piece of wood"
    (1, 2), -- "Mega Pack" contains "How to live this life Pack"
    (2, 1), -- "Ultra Pack" contains "How to make a nuclear reactor out of noodles and a piece of wood"
    (2, 2), -- "Ultra Pack" contains "How to live this life Pack"
    (2, 3), -- "Ultra Pack" contains "How to be born in rich family Pack"
    (2, 4), -- "Ultra Pack" contains "How to use microwave as entertainment"
    (3, 1), -- "Unbelievably huge Pack" contains "How to make a nuclear reactor out of noodles and a piece of wood"
    (3, 2), -- "Unbelievably huge Pack" contains "How to live this life Pack"
    (3, 3), -- "Unbelievably huge Pack" contains "How to be born in rich family Pack"
    (3, 4), -- "Unbelievably huge Pack" contains "How to use microwave as entertainment"
    (3, 5); -- "Unbelievably huge Pack" contains "How to train hamsters Pack"
