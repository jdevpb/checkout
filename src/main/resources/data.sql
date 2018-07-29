insert into item_master (item_cd, item_name) 
values 
(1, 'MacBook'),
(2, 'Corn Flakes'),
(3, 'Cooking Oil');

insert into locale_details (locale_details_id, locale, locale_name, currency_cd, currency, currency_symbol)
values
(100000, 'en_US', 'US English', 'USD', 'US Dollars', '$'),
(100001, 'fr_FR', 'French', 'CFP', 'Franc', '₣');

insert into tax_details (tax_details_id, tax_cd, tax_description, tax_rate)
values
(10000, 'ST10', 'Sales Tax', 10.0),
(10001, 'ST10FR', 'Sales Tax (France)', 10.0),
(10002, 'ST20', 'Sales Tax', 20.0),
(10003, 'ST20FR', 'Sales Tax (France)', 20.0),
(10004, 'ST00', 'Sales Tax', 00.0),
(10005, 'ST00FR', 'Sales Tax (France)', 00.0);

insert into item_tax_details (item_tax_details_id, item_category, tax_cd, locale) 
values
(1000, 'Category A', 'ST10', 'en_US'),
(1001, 'Category A', 'ST10FR', 'fr_FR'),
(1002, 'Category B', 'ST20', 'en_US'),
(1003, 'Category B', 'ST20FR', 'fr_FR'),
(1004, 'Category C', 'ST00', 'en_US'),
(1005, 'Category C', 'ST00FR', 'fr_FR');

insert into item_details (item_details_id, item_cd, item_description, item_price, item_category, locale) 
values
(100, 1, 'MacBook Pro 15', 2000.00, 'Category A', 'en_US'),
(101, 1, 'MacBook Pro 15(French)', 1600.00, 'Category A', 'fr_FR'),
(102, 2, 'Kellogs Original', 2.00, 'Category B', 'en_US'),
(103, 2, 'Kellogs Original (French)', 1.80, 'Category B', 'fr_FR'),
(104, 3, 'Saffola Vegetable Oil', 9.00, 'Category C', 'en_US'),
(105, 3, 'Saffola Vegetable Oil (French)', 7.20, 'Category C', 'fr_FR');

