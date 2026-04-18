INSERT INTO battery_temp (id, temp, set_temp) VALUES (1, 20, 23);
INSERT INTO battery_temp (id, temp, set_temp) VALUES (2, -20, -23);
INSERT INTO battery_settings (id, min_temp_id, max_temp_id) VALUES (1, 1, 2);
INSERT INTO window_blind_action (id, status, time_start, time_end)
VALUES (1, 'COMPLETED', NOW(), NOW());
INSERT INTO window_blind_settings (id, open_action_id, close_action_id)
VALUES (1, 1, 1);