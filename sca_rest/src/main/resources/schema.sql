CREATE TABLE IF NOT EXISTS meal_entry (
  meal_id BIGINT NOT NULL AUTO_INCREMENT,
  meal_name VARCHAR(100) NOT NULL,
  calories DOUBLE NOT NULL,
  meal_type VARCHAR(100),
  user_id VARCHAR(50) NOT NULL,
  PRIMARY KEY (meal_id)
);

CREATE TABLE IF NOT EXISTS  food_entry (
  user_id VARCHAR(50) NOT NULL,
  meal_id BIGINT NOT NULL,
  food_id BIGINT NOT NULL AUTO_INCREMENT,
  fdate DATE NOT NULL,
  FOREIGN KEY (meal_id) REFERENCES meal_entry(meal_id),
  PRIMARY KEY (food_id)
);

/*CREATE TABLE IF NOT EXISTS user_diet_constraints (
    user_id VARCHAR(50) NOT NULL,
    threshold_cal_limit DOUBLE NOT NULL,
    breakfast_limit int NOT NULL,
    lunch_limit int NOT NULL,
    dinner_limit int NOT NULL,
    PRIMARY KEY (user_id)
);*/

