INSERT INTO meal_entry (meal_id, meal_name, calories, meal_type, user_id) values
    (1, 'Burger', 10, 'B', 'jack'),
    (2, 'Pizza', 10, 'L', 'jack'),
    (3, 'Oat', 10, 'D', 'jack'),
    (4, 'Oat', 10, null ,'jack');
INSERT INTO food_entry(user_id, meal_id, food_id, fdate) values
    ('jack', 1,1, TO_DATE('19/04/2022', 'DD/MM/YYYY')),
    ('jack', 2,2, TO_DATE('19/04/2022', 'DD/MM/YYYY')),
    ('jack', 4,3, TO_DATE('20/04/2022', 'DD/MM/YYYY'));

