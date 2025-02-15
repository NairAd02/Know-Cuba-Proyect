PGDMP  0                     |            know_cuba_db_3    16.0    16.0 �              0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    116021    know_cuba_db_3    DATABASE     �   CREATE DATABASE know_cuba_db_3 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE know_cuba_db_3;
                postgres    false                        3079    116022    pgcrypto 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;
    DROP EXTENSION pgcrypto;
                   false                       0    0    EXTENSION pgcrypto    COMMENT     <   COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';
                        false    2            C           1255    116059 ?   change_state_password_user(integer, boolean, character varying)    FUNCTION     �  CREATE FUNCTION public.change_state_password_user(_id_user integer, _state_password boolean, _new_password character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE
_password_return character varying;
BEGIN
UPDATE public.user SET state_password = _state_password, 
user_password = (PGP_SYM_ENCRYPT(_new_password, 'AES_KEY')) 
WHERE id = _id_user RETURNING user_password INTO _password_return;

RETURN _password_return;

END;
$$;
 }   DROP FUNCTION public.change_state_password_user(_id_user integer, _state_password boolean, _new_password character varying);
       public          postgres    false            �           1255    116060    close_contract(integer, date)    FUNCTION       CREATE FUNCTION public.close_contract(_id_contract integer, _contract_reconcilation_date date) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE contract SET contract_reconcilation_date = _contract_reconcilation_date,
state = TRUE WHERE id_contract = _id_contract;
END;$$;
 ^   DROP FUNCTION public.close_contract(_id_contract integer, _contract_reconcilation_date date);
       public          postgres    false            �           1255    116061    delete_activity(integer)    FUNCTION     �   CREATE FUNCTION public.delete_activity(_id_activity integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM activity WHERE id_activity = _id_activity;
END;
$$;
 <   DROP FUNCTION public.delete_activity(_id_activity integer);
       public          postgres    false            �           1255    116062    delete_all_user()    FUNCTION     }   CREATE FUNCTION public.delete_all_user() RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM public.user;
END;
$$;
 (   DROP FUNCTION public.delete_all_user();
       public          postgres    false            �           1255    116063    delete_contract(integer)    FUNCTION     �   CREATE FUNCTION public.delete_contract(_id_contract integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM contract WHERE id_contract = _id_contract;
END;$$;
 <   DROP FUNCTION public.delete_contract(_id_contract integer);
       public          postgres    false            �           1255    116064    delete_hotel_modality(integer)    FUNCTION     �   CREATE FUNCTION public.delete_hotel_modality(_hotel_modality_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM hotel_modality AS hm WHERE hm.id = _hotel_modality_id;
END;
$$;
 H   DROP FUNCTION public.delete_hotel_modality(_hotel_modality_id integer);
       public          postgres    false            �           1255    116065 2   delete_hotel_modality_from_hotel(integer, integer)    FUNCTION       CREATE FUNCTION public.delete_hotel_modality_from_hotel(_hotel_id integer, _hotel_modality_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM hotel_hotel_modality AS hm WHERE hm.hotel_id = _hotel_id  AND 
hm.hotel_modality_id = _hotel_modality_id;
END;
$$;
 f   DROP FUNCTION public.delete_hotel_modality_from_hotel(_hotel_id integer, _hotel_modality_id integer);
       public          postgres    false            d           1255    116066    delete_meal_plan(integer)    FUNCTION     �   CREATE FUNCTION public.delete_meal_plan(_id_meal_plan integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM meal_plan WHERE id = _id_meal_plan;
END;$$;
 >   DROP FUNCTION public.delete_meal_plan(_id_meal_plan integer);
       public          postgres    false                       1255    116067 (   delete_meal_plan_hotel(integer, integer)    FUNCTION     �   CREATE FUNCTION public.delete_meal_plan_hotel(_hotel_id integer, _meal_plan_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM hotel_meal_plan 
WHERE hotel_id = _hotel_id AND meal_plan_id = _meal_plan_id;
END;$$;
 W   DROP FUNCTION public.delete_meal_plan_hotel(_hotel_id integer, _meal_plan_id integer);
       public          postgres    false            |           1255    116068    delete_modality(integer)    FUNCTION     �   CREATE FUNCTION public.delete_modality(_id_modality integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM modality WHERE id = _id_modality;
END;$$;
 <   DROP FUNCTION public.delete_modality(_id_modality integer);
       public          postgres    false            J           1255    116069 1   delete_modality_tourist_package(integer, integer)    FUNCTION       CREATE FUNCTION public.delete_modality_tourist_package(_modality_id integer, _tourist_package_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM tourist_package_modality 
WHERE modality_id = _modality_id AND tourist_package_id = _tourist_package_id;
END;$$;
 i   DROP FUNCTION public.delete_modality_tourist_package(_modality_id integer, _tourist_package_id integer);
       public          postgres    false            A           1255    116070    delete_provider(integer)    FUNCTION     �   CREATE FUNCTION public.delete_provider(_id_provider integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM provider WHERE id_provider = _id_provider;
END;$$;
 <   DROP FUNCTION public.delete_provider(_id_provider integer);
       public          postgres    false            �           1255    116071 8   delete_reference_modality_table_accommodation_modality()    FUNCTION     �   CREATE FUNCTION public.delete_reference_modality_table_accommodation_modality() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
PERFORM PUBLIC.delete_modality (OLD.id);

RETURN OLD;
END;
$$;
 O   DROP FUNCTION public.delete_reference_modality_table_accommodation_modality();
       public          postgres    false            )           1255    116072 2   delete_reference_modality_table_service_modality()    FUNCTION     �   CREATE FUNCTION public.delete_reference_modality_table_service_modality() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
PERFORM PUBLIC.delete_modality (OLD.modality_id);

RETURN OLD;

END;
$$;
 I   DROP FUNCTION public.delete_reference_modality_table_service_modality();
       public          postgres    false            N           1255    116073 4   delete_reference_modality_table_transport_modality()    FUNCTION     �   CREATE FUNCTION public.delete_reference_modality_table_transport_modality() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
PERFORM PUBLIC.delete_modality (OLD.modality_id);

RETURN OLD;

END;
$$;
 K   DROP FUNCTION public.delete_reference_modality_table_transport_modality();
       public          postgres    false            �           1255    116074    delete_request(integer)    FUNCTION     �   CREATE FUNCTION public.delete_request(_id_request integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM public.request AS r WHERE r.id = _id_request;
END;$$;
 :   DROP FUNCTION public.delete_request(_id_request integer);
       public          postgres    false            z           1255    116075    delete_rol(integer)    FUNCTION     �   CREATE FUNCTION public.delete_rol(_id_rol integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM public.rol WHERE id = _id_rol;
END;$$;
 2   DROP FUNCTION public.delete_rol(_id_rol integer);
       public          postgres    false            O           1255    116076    delete_season(integer)    FUNCTION     �   CREATE FUNCTION public.delete_season(_id_season integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM season WHERE id = _id_season;
END;
$$;
 8   DROP FUNCTION public.delete_season(_id_season integer);
       public          postgres    false            /           1255    116077 =   delete_selected_plan_accommodation_modality(integer, integer)    FUNCTION     X  CREATE FUNCTION public.delete_selected_plan_accommodation_modality(_selected_plans_id integer, _accommodation_modality_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM selected_plan_accommodation_modality 
WHERE selected_plans_id = _selected_plans_id 
AND accommodation_modality_id = _accommodation_modality_id;
END;$$;
 �   DROP FUNCTION public.delete_selected_plan_accommodation_modality(_selected_plans_id integer, _accommodation_modality_id integer);
       public          postgres    false            Q           1255    116078    delete_selected_plans(integer)    FUNCTION     �   CREATE FUNCTION public.delete_selected_plans(_selected_plans_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM selected_plans WHERE id = _selected_plans_id;
END;$$;
 H   DROP FUNCTION public.delete_selected_plans(_selected_plans_id integer);
       public          postgres    false            1           1255    116079    delete_tourist_package(integer)    FUNCTION     �   CREATE FUNCTION public.delete_tourist_package(_id_tourist_package integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM tourist_package WHERE id_tourist_package = _id_tourist_package;
END;$$;
 J   DROP FUNCTION public.delete_tourist_package(_id_tourist_package integer);
       public          postgres    false            �           1255    116080    delete_type_of_room(integer)    FUNCTION     �   CREATE FUNCTION public.delete_type_of_room(_id_type_of_room integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM type_of_room WHERE id = _id_type_of_room;
END;$$;
 D   DROP FUNCTION public.delete_type_of_room(_id_type_of_room integer);
       public          postgres    false            q           1255    116081 +   delete_type_of_room_hotel(integer, integer)    FUNCTION     �   CREATE FUNCTION public.delete_type_of_room_hotel(_hotel_id integer, _type_of_room_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM hotel_type_of_room 
WHERE hotel_id = _hotel_id AND type_of_room_id = _type_of_room_id;
END;$$;
 ]   DROP FUNCTION public.delete_type_of_room_hotel(_hotel_id integer, _type_of_room_id integer);
       public          postgres    false            �           1255    116082    delete_user(integer)    FUNCTION     �   CREATE FUNCTION public.delete_user(_id_user integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM public.user WHERE id = _id_user;
END;$$;
 4   DROP FUNCTION public.delete_user(_id_user integer);
       public          postgres    false            T           1255    116083    delete_vehicle(integer)    FUNCTION     �   CREATE FUNCTION public.delete_vehicle(_id_vehicle integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM vehicle WHERE id_vehicle = _id_vehicle;
END;$$;
 :   DROP FUNCTION public.delete_vehicle(_id_vehicle integer);
       public          postgres    false            L           1255    116084 8   delete_vehicle_from_transport_modality(integer, integer)    FUNCTION     (  CREATE FUNCTION public.delete_vehicle_from_transport_modality(_id_transport_modality integer, _id_vehicle integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
DELETE FROM transport_modality_vehicle WHERE transport_modality_id = _id_transport_modality
AND vehicle_id = _id_vehicle;
END;
$$;
 r   DROP FUNCTION public.delete_vehicle_from_transport_modality(_id_transport_modality integer, _id_vehicle integer);
       public          postgres    false            �           1255    116085 #   get_accommodation_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_accommodation_contract(_id_accommodation_contract integer) RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, hotel_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
ac.hotel_id, c.surcharge FROM accommodation_contract AS ac INNER JOIN contract AS c 
ON ac.id_contract = c.id_contract WHERE ac.id_contract = _id_accommodation_contract ;
END;
$$;
 U   DROP FUNCTION public.get_accommodation_contract(_id_accommodation_contract integer);
       public          postgres    false            �           1255    116086 #   get_accommodation_modality(integer)    FUNCTION     �  CREATE FUNCTION public.get_accommodation_modality(_id_accommodation_modality integer) RETURNS TABLE(id integer, accommodation_contract_id integer, type_of_modality character varying, type_of_room_id integer, meal_plan_id integer, price double precision, cant_days_accommodation integer, hotel_modality_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id, a.accommodation_contract_id, m.type_of_modality, a.type_of_room_id, a.meal_plan_id, a.price, a.cant_days_accommodation, a.hotel_modality_id 
FROM accommodation_modality AS a INNER JOIN modality AS m 
ON a.id = m.id 
WHERE a.id = _id_accommodation_modality;
END;
$$;
 U   DROP FUNCTION public.get_accommodation_modality(_id_accommodation_modality integer);
       public          postgres    false            �           1255    116087 :   get_accommodation_modality_accommodation_contract(integer)    FUNCTION     q  CREATE FUNCTION public.get_accommodation_modality_accommodation_contract(_id_accommodation_contract integer) RETURNS TABLE(id integer, type_of_modality character varying, type_of_room_id integer, meal_plan_id integer, price double precision, cant_days_accommodation integer, hotel_modality_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id, m.type_of_modality, a.type_of_room_id, a.meal_plan_id, a.price, a.cant_days_accommodation, a.hotel_modality_id 
FROM accommodation_modality AS a INNER JOIN modality AS m 
ON a.id = m.id 
WHERE a.accommodation_contract_id = _id_accommodation_contract;
END;
$$;
 l   DROP FUNCTION public.get_accommodation_modality_accommodation_contract(_id_accommodation_contract integer);
       public          postgres    false            v           1255    116088 3   get_accommodation_modality_tourist_package(integer)    FUNCTION     �  CREATE FUNCTION public.get_accommodation_modality_tourist_package(_tourist_package_id integer) RETURNS TABLE(id integer, type_of_modality character varying, accommodation_contract_id integer, type_of_room_id integer, meal_plan_id integer, price double precision, cant_days_accommodation integer, hotel_modality_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id, m.type_of_modality, a.accommodation_contract_id, a.type_of_room_id, a.meal_plan_id, a.price, a.cant_days_accommodation, a.hotel_modality_id 
FROM tourist_package_modality AS tpm INNER JOIN accommodation_modality AS a 
ON tpm.modality_id = a.id
INNER JOIN modality AS m 
ON a.id = m.id 
WHERE tpm.tourist_package_id = _tourist_package_id;
END;
$$;
 ^   DROP FUNCTION public.get_accommodation_modality_tourist_package(_tourist_package_id integer);
       public          postgres    false            �           1255    116089    get_activity(integer)    FUNCTION     x  CREATE FUNCTION public.get_activity(_id_activity integer) RETURNS TABLE(id_activity integer, activity_description character varying, service_provider_id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id_activity, a.activity_description, a.service_provider_id, a.name
FROM activity AS a WHERE a.id_activity = _id_activity;
END;
$$;
 9   DROP FUNCTION public.get_activity(_id_activity integer);
       public          postgres    false            I           1255    116090 &   get_activity_service_provider(integer)    FUNCTION     �  CREATE FUNCTION public.get_activity_service_provider(_id_service_provider integer) RETURNS TABLE(id_activity integer, activity_description character varying, service_provider_id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id_activity, a.activity_description, a.service_provider_id, a.name
FROM activity AS a WHERE a.service_provider_id = _id_service_provider;
END;
$$;
 R   DROP FUNCTION public.get_activity_service_provider(_id_service_provider integer);
       public          postgres    false            ,           1255    116091    get_administrator(integer)    FUNCTION     7  CREATE FUNCTION public.get_administrator(_id_user integer) RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT a.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.administrator AS a ON a.user_id = u.id
WHERE a.user_id = _id_user;
END;
$$;
 :   DROP FUNCTION public.get_administrator(_id_user integer);
       public          postgres    false            .           1255    116092     get_all_accommodation_contract()    FUNCTION     �  CREATE FUNCTION public.get_all_accommodation_contract() RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, hotel_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
ac.hotel_id, c.surcharge FROM accommodation_contract AS ac INNER JOIN contract AS c 
ON ac.id_contract = c.id_contract;
END;
$$;
 7   DROP FUNCTION public.get_all_accommodation_contract();
       public          postgres    false            �           1255    116093     get_all_accommodation_modality()    FUNCTION     |  CREATE FUNCTION public.get_all_accommodation_modality() RETURNS TABLE(id integer, type_of_modality character varying, accommodation_contract_id integer, type_of_room_id integer, meal_plan_id integer, price double precision, cant_days_accommodation integer, hotel_modality_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id, m.type_of_modality, a.accommodation_contract_id, a.type_of_room_id, a.meal_plan_id, a.price, a.cant_days_accommodation, a.hotel_modality_id 
FROM accommodation_modality AS a INNER JOIN modality AS m 
ON a.id = m.id 
WHERE a.accommodation_contract_id = _id_accommodation_contract;
END;
$$;
 7   DROP FUNCTION public.get_all_accommodation_modality();
       public          postgres    false            �           1255    116094    get_all_activity()    FUNCTION     E  CREATE FUNCTION public.get_all_activity() RETURNS TABLE(id_activity integer, activity_description character varying, service_provider_id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select a.id_activity, a.activity_description, a.service_provider_id, a.name
FROM activity as a;
END;
$$;
 )   DROP FUNCTION public.get_all_activity();
       public          postgres    false            8           1255    116095    get_all_administrator()    FUNCTION       CREATE FUNCTION public.get_all_administrator() RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT a.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.administrator AS a ON a.user_id = u.id;
END;
$$;
 .   DROP FUNCTION public.get_all_administrator();
       public          postgres    false            �           1255    116096    get_all_carrier_contract()    FUNCTION     �  CREATE FUNCTION public.get_all_carrier_contract() RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, transportation_provider_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
cc.transportation_provider_id, c.surcharge FROM carrier_contract AS cc INNER JOIN contract AS c 
ON cc.id_contract = c.id_contract;
END;
$$;
 1   DROP FUNCTION public.get_all_carrier_contract();
       public          postgres    false            �           1255    116097    get_all_dependent()    FUNCTION       CREATE FUNCTION public.get_all_dependent() RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT d.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.dependent AS d ON d.user_id = u.id;
END;
$$;
 *   DROP FUNCTION public.get_all_dependent();
       public          postgres    false            x           1255    116098    get_all_hotel()    FUNCTION     �  CREATE FUNCTION public.get_all_hotel() RETURNS TABLE(id integer, name character varying, province character varying, hotel_chain character varying, address character varying, hotel_category integer, phone integer, fax character varying, email character varying, cant_rooms integer, cant_floors integer, location_hotel character varying, distance_nearest_city double precision, distance_airport double precision, date_built date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select h.id, p.name, p.province, h.hotel_chain, h.address, h.hotel_category
, h.phone, h.fax, h.email, h.cant_rooms, h.cant_floors, h.location_hotel, h.distance_nearest_city
, h.distance_airport, h.date_built  from hotel AS h INNER JOIN provider AS p
ON h.id = p.id_provider;
END;
$$;
 &   DROP FUNCTION public.get_all_hotel();
       public          postgres    false            4           1255    116099    get_all_hotel_modality()    FUNCTION     �   CREATE FUNCTION public.get_all_hotel_modality() RETURNS TABLE(id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select hm.id, hm.name FROM hotel_modality AS hm;
END;
$$;
 /   DROP FUNCTION public.get_all_hotel_modality();
       public          postgres    false                       1255    116100    get_all_manager()    FUNCTION       CREATE FUNCTION public.get_all_manager() RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT m.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password   
FROM public.user AS u INNER JOIN public.manager AS m ON m.user_id = u.id;
END;
$$;
 (   DROP FUNCTION public.get_all_manager();
       public          postgres    false            �           1255    116101    get_all_meal_plan()    FUNCTION     �   CREATE FUNCTION public.get_all_meal_plan() RETURNS TABLE(id integer, meal_plan_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.meal_plan_name FROM meal_plan AS m;
END;
$$;
 *   DROP FUNCTION public.get_all_meal_plan();
       public          postgres    false            �           1255    116102    get_all_package_designer()    FUNCTION       CREATE FUNCTION public.get_all_package_designer() RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT p.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.package_designer AS p ON p.user_id = u.id;
END;
$$;
 1   DROP FUNCTION public.get_all_package_designer();
       public          postgres    false            K           1255    116103 !   get_all_password_change_request()    FUNCTION     c  CREATE FUNCTION public.get_all_password_change_request() RETURNS TABLE(id integer, type_request character varying, date_request date, user_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.type_request, r.date_request, r.user_id FROM public.request AS r INNER JOIN
public.password_change_request AS pr ON r.id = pr.id;
END;
$$;
 8   DROP FUNCTION public.get_all_password_change_request();
       public          postgres    false            �           1255    116104    get_all_rol()    FUNCTION     �   CREATE FUNCTION public.get_all_rol() RETURNS TABLE(id integer, rol_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.rol_name from public.rol as r;
END;
$$;
 $   DROP FUNCTION public.get_all_rol();
       public          postgres    false            �           1255    116105 $   get_all_rol_diferent_administrator()    FUNCTION     �   CREATE FUNCTION public.get_all_rol_diferent_administrator() RETURNS TABLE(id integer, rol_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.rol_name from public.rol as r where r.id != 1;
END;
$$;
 ;   DROP FUNCTION public.get_all_rol_diferent_administrator();
       public          postgres    false            "           1255    116106    get_all_seasons()    FUNCTION     �  CREATE FUNCTION public.get_all_seasons() RETURNS TABLE(id integer, season_name character varying, season_start_date character varying, season_termination_date character varying, season_description character varying, type_of_season character varying, accommodation_contract_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select id, season_name, season_start_date, season_termination_date, season_description, type_of_season, accommodation_contract_id
FROM season;
END;
$$;
 (   DROP FUNCTION public.get_all_seasons();
       public          postgres    false                       1255    116107    get_all_service_contract()    FUNCTION     �  CREATE FUNCTION public.get_all_service_contract() RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, service_provider_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
sc.service_provider_id, c.surcharge FROM service_contract AS sc INNER JOIN contract AS c 
ON sc.id_contract = c.id_contract;
END;
$$;
 1   DROP FUNCTION public.get_all_service_contract();
       public          postgres    false            }           1255    116108    get_all_service_modality()    FUNCTION     �  CREATE FUNCTION public.get_all_service_modality() RETURNS TABLE(modality_id integer, type_of_modality character varying, service_contract_id integer, activity_id integer, price double precision, release_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, sm.service_contract_id, sm.activity_id, sm.price, sm.release_date
FROM service_modality AS sm INNER JOIN modality AS m ON sm.modality_id = m.id;
END;
$$;
 1   DROP FUNCTION public.get_all_service_modality();
       public          postgres    false            �           1255    116109    get_all_service_provider()    FUNCTION     1  CREATE FUNCTION public.get_all_service_provider() RETURNS TABLE(id integer, service_name character varying, province character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select s.id, p.name, p.province from service_provider AS s INNER JOIN provider AS p 
ON s.id = p.id_provider;
END;
$$;
 1   DROP FUNCTION public.get_all_service_provider();
       public          postgres    false            X           1255    116110    get_all_tourist_package()    FUNCTION     �  CREATE FUNCTION public.get_all_tourist_package() RETURNS TABLE(id_tourist_package integer, name character varying, cant_max_pax integer, cant_reserves integer, start_date date, termination_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT tp.id_tourist_package, tp.name, tp.cant_max_pax, tp.cant_reserves, tp.start_date, tp.termination_date  
FROM tourist_package AS tp;
END;
$$;
 0   DROP FUNCTION public.get_all_tourist_package();
       public          postgres    false                       1255    116111 ,   get_all_transport_modality_cost_kilometers()    FUNCTION     �  CREATE FUNCTION public.get_all_transport_modality_cost_kilometers() RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_going double precision, cost_kilometers_lap double precision, cost_hours_wait double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, ck.cost_kilometers_going, ck.cost_kilometers_lap, 
ck.cost_hours_wait FROM cost_kilometers AS ck
INNER JOIN transport_modality AS tm 
ON ck.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id;
END;
$$;
 C   DROP FUNCTION public.get_all_transport_modality_cost_kilometers();
       public          postgres    false                       1255    116112 .   get_all_transport_modality_established_route()    FUNCTION     �  CREATE FUNCTION public.get_all_transport_modality_established_route() RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, description_rout character varying, cost_going double precision, cost_lap double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, er.description_rout, er.cost_going, 
er.cost_lap FROM established_route AS er
INNER JOIN transport_modality AS tm 
ON er.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id;
END;
$$;
 E   DROP FUNCTION public.get_all_transport_modality_established_route();
       public          postgres    false            @           1255    116113 -   get_all_transport_modality_hours_kilometers()    FUNCTION       CREATE FUNCTION public.get_all_transport_modality_hours_kilometers() RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_rout double precision, cost_hours double precision, cost_kilometers_rout_additionals double precision, cost_hours_additionals double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, hk.cost_kilometers_rout, hk.cost_hours, hk.cost_kilometers_rout_additionals,
hk.cost_hours_additionals FROM hours_kilometers AS hk
INNER JOIN transport_modality AS tm 
ON hk.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id;
END;
$$;
 D   DROP FUNCTION public.get_all_transport_modality_hours_kilometers();
       public          postgres    false            �           1255    116114 !   get_all_transportation_provider()    FUNCTION     @  CREATE FUNCTION public.get_all_transportation_provider() RETURNS TABLE(id integer, provider_name character varying, province character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select t.id, p.name, p.province from transportation_provider AS t INNER JOIN provider AS P 
ON t.id = p.id_provider;
END;
$$;
 8   DROP FUNCTION public.get_all_transportation_provider();
       public          postgres    false            !           1255    116115    get_all_type_of_room()    FUNCTION     �   CREATE FUNCTION public.get_all_type_of_room() RETURNS TABLE(id integer, type_of_room_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select t.id, t.type_of_room_name FROM type_of_room AS t;
END;
$$;
 -   DROP FUNCTION public.get_all_type_of_room();
       public          postgres    false            �           1255    116116    get_all_vehicle()    FUNCTION       CREATE FUNCTION public.get_all_vehicle() RETURNS TABLE(id_vehicle integer, lock character varying, transportation_provider_id integer, brand character varying, capacity_without_luggage integer, capacity_with_luggage integer, date_of_production date, total_capacity integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT v.id_vehicle, v.lock, v.transportation_provider_id, v.brand,
v.capacity_without_luggage, v.capacity_with_luggage, v.capacity_with_luggage, v.date_of_production,
v.total_capacity
FROM vehicle AS v;
END;
$$;
 (   DROP FUNCTION public.get_all_vehicle();
       public          postgres    false            [           1255    116117    get_carrier_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_carrier_contract(_id_carrier_contract integer) RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, transportation_provider_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
cc.transportation_provider_id, c.surcharge FROM carrier_contract AS cc INNER JOIN contract AS c 
ON cc.id_contract = c.id_contract WHERE cc.id_contract = _id_carrier_contract;
END;
$$;
 I   DROP FUNCTION public.get_carrier_contract(_id_carrier_contract integer);
       public          postgres    false            5           1255    116118    get_dependent(integer)    FUNCTION     /  CREATE FUNCTION public.get_dependent(_id_user integer) RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT d.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.dependent AS d ON d.user_id = u.id
WHERE d.user_id = _id_user;
END;
$$;
 6   DROP FUNCTION public.get_dependent(_id_user integer);
       public          postgres    false            �           1255    116119    get_hotel(integer)    FUNCTION       CREATE FUNCTION public.get_hotel(_id_hotel integer) RETURNS TABLE(id integer, name character varying, province character varying, hotel_chain character varying, address character varying, hotel_category integer, phone integer, fax character varying, email character varying, cant_rooms integer, cant_floors integer, location_hotel character varying, distance_nearest_city double precision, distance_airport double precision, date_built date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select h.id, p.name, p.province, h.hotel_chain, h.address, h.hotel_category
, h.phone, h.fax, h.email, h.cant_rooms, h.cant_floors, h.location_hotel, h.distance_nearest_city
, h.distance_airport, h.date_built
from hotel AS h INNER JOIN provider AS p
ON h.id = p.id_provider WHERE h.id = _id_hotel;
END;
$$;
 3   DROP FUNCTION public.get_hotel(_id_hotel integer);
       public          postgres    false            �           1255    116120    get_hotel_modality(integer)    FUNCTION       CREATE FUNCTION public.get_hotel_modality(_id_hotel_modality integer) RETURNS TABLE(id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select hm.id, hm.name FROM hotel_modality AS hm WHERE hm.id = _id_hotel_modality;
END;
$$;
 E   DROP FUNCTION public.get_hotel_modality(_id_hotel_modality integer);
       public          postgres    false            ]           1255    116121 &   get_hotel_modality_from_hotel(integer)    FUNCTION     O  CREATE FUNCTION public.get_hotel_modality_from_hotel(_id_hotel integer) RETURNS TABLE(id integer, name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT hm.id, hm.name FROM  hotel_hotel_modality AS hhm 
INNER JOIN hotel_modality AS hm ON hhm.hotel_modality_id = hm.id WHERE hhm.hotel_id = _id_hotel;
END;
$$;
 G   DROP FUNCTION public.get_hotel_modality_from_hotel(_id_hotel integer);
       public          postgres    false                       1255    116122    get_manager(integer)    FUNCTION     +  CREATE FUNCTION public.get_manager(_id_user integer) RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT m.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u INNER JOIN public.manager AS m ON m.user_id = u.id
WHERE m.user_id = _id_user;
END;
$$;
 4   DROP FUNCTION public.get_manager(_id_user integer);
       public          postgres    false                       1255    116123    get_meal_plan(integer)    FUNCTION       CREATE FUNCTION public.get_meal_plan(_id_meal_plan integer) RETURNS TABLE(id integer, meal_plan_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.meal_plan_name FROM meal_plan AS m WHERE m.id = _id_meal_plan;
END;
$$;
 ;   DROP FUNCTION public.get_meal_plan(_id_meal_plan integer);
       public          postgres    false                       1255    116124    get_meal_plans_hotel(integer)    FUNCTION     ?  CREATE FUNCTION public.get_meal_plans_hotel(_id_hotel integer) RETURNS TABLE(id integer, meal_plan_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.meal_plan_name from hotel_meal_plan as hm inner join meal_plan m on hm.meal_plan_id = m.id
WHERE hm.hotel_id = _id_hotel;
END;
$$;
 >   DROP FUNCTION public.get_meal_plans_hotel(_id_hotel integer);
       public          postgres    false                       1255    116125 0   get_modalitys_services_service_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_modalitys_services_service_contract(_id_service_contract integer) RETURNS TABLE(modality_id integer, activity_id integer, price double precision, type_of_modality character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select sm.modality_id, sm.activity_id, sm.price, m.type_of_modality 
FROM service_modality as sm INNER JOIN modality as m ON sm.modality_id = m.id
WHERE sm.service_contract_id = _id_service_contract;
END;
$$;
 \   DROP FUNCTION public.get_modalitys_services_service_contract(_id_service_contract integer);
       public          postgres    false            �           1255    116126    get_package_designer(integer)    FUNCTION     >  CREATE FUNCTION public.get_package_designer(_id_user integer) RETURNS TABLE(id integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT p.user_id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password   
FROM public.user AS u INNER JOIN public.package_designer AS p ON p.user_id = u.id
WHERE p.user_id = _id_user;
END;
$$;
 =   DROP FUNCTION public.get_package_designer(_id_user integer);
       public          postgres    false            a           1255    116127 $   get_password_change_request(integer)    FUNCTION     �  CREATE FUNCTION public.get_password_change_request(_id_password_change_request integer) RETURNS TABLE(id integer, type_request character varying, date_request date, user_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.type_request, r.date_request, r.user_id FROM public.request AS r INNER JOIN
public.password_change_request AS pr ON r.id = pr.id WHERE pr.id = _id_password_change_request;
END;
$$;
 W   DROP FUNCTION public.get_password_change_request(_id_password_change_request integer);
       public          postgres    false                       1255    116128 )   get_password_change_request_user(integer)    FUNCTION     �  CREATE FUNCTION public.get_password_change_request_user(_id_user integer) RETURNS TABLE(id integer, type_request character varying, date_request date, user_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.type_request, r.date_request, r.user_id FROM public.request AS r INNER JOIN
public.password_change_request AS pr ON r.id = pr.id WHERE r.user_id = _id_user;
END;
$$;
 I   DROP FUNCTION public.get_password_change_request_user(_id_user integer);
       public          postgres    false            l           1255    116129    get_rol(integer)    FUNCTION     �   CREATE FUNCTION public.get_rol(_id_rol integer) RETURNS TABLE(id integer, rol_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select r.id, r.rol_name from public.rol as r where r.id = _id_rol;
END;
$$;
 /   DROP FUNCTION public.get_rol(_id_rol integer);
       public          postgres    false            �           1255    116130    get_season(integer)    FUNCTION     
  CREATE FUNCTION public.get_season(_id_season integer) RETURNS TABLE(id integer, season_name character varying, season_start_date character varying, season_termination_date character varying, season_description character varying, type_of_season character varying, accommodation_contract_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select id, season_name, season_start_date, season_termination_date, season_description, type_of_season, accommodation_contract_id
FROM season WHERE id = _id_season;
END;
$$;
 5   DROP FUNCTION public.get_season(_id_season integer);
       public          postgres    false            9           1255    116131 +   get_seasons_accommodation_contract(integer)    FUNCTION     T  CREATE FUNCTION public.get_seasons_accommodation_contract(_id_accommodation_contract integer) RETURNS TABLE(id integer, season_name character varying, season_start_date date, season_termination_date date, season_description character varying, type_of_season character varying, accommodation_contract_id integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select s.id, s.season_name, s.season_start_date, s.season_termination_date, s.season_description, s.type_of_season, s.accommodation_contract_id
FROM season AS s WHERE s.accommodation_contract_id = _id_accommodation_contract;
END;
$$;
 ]   DROP FUNCTION public.get_seasons_accommodation_contract(_id_accommodation_contract integer);
       public          postgres    false            �           1255    116132    get_service_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_service_contract(_id_service_contract integer) RETURNS TABLE(id_contract integer, contract_start_date date, contract_termination_date date, contract_description character varying, type_of_contract character varying, state boolean, contract_reconcilation_date date, service_provider_id integer, surcharge double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select c.id_contract, c.contract_start_date, c.contract_termination_date, 
c.contract_description, c.type_of_contract, c.state, c.contract_reconcilation_date, 
sc.service_provider_id, c.surcharge FROM service_contract AS sc INNER JOIN contract AS c 
ON sc.id_contract = c.id_contract WHERE sc.id_contract = _id_service_contract;
END;
$$;
 I   DROP FUNCTION public.get_service_contract(_id_service_contract integer);
       public          postgres    false                       1255    116133    get_service_modality(integer)    FUNCTION     �  CREATE FUNCTION public.get_service_modality(_id_modality integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, service_contract_id integer, activity_id integer, price double precision, release_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, sm.service_contract_id, sm.activity_id, sm.price, sm.release_date
FROM service_modality AS sm INNER JOIN modality AS m ON sm.modality_id = m.id
WHERE sm.modality_id = _id_modality;
END;
$$;
 A   DROP FUNCTION public.get_service_modality(_id_modality integer);
       public          postgres    false            m           1255    116134 .   get_service_modality_service_contract(integer)    FUNCTION       CREATE FUNCTION public.get_service_modality_service_contract(_id_contract integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, service_contract_id integer, activity_id integer, price double precision, release_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, sm.service_contract_id, sm.activity_id, sm.price, sm.release_date
FROM service_modality AS sm INNER JOIN modality AS m ON sm.modality_id = m.id
WHERE sm.service_contract_id = _id_contract;
END;
$$;
 R   DROP FUNCTION public.get_service_modality_service_contract(_id_contract integer);
       public          postgres    false            ?           1255    116135 -   get_service_modality_tourist_package(integer)    FUNCTION     l  CREATE FUNCTION public.get_service_modality_tourist_package(_id_tourist_package integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, service_contract_id integer, activity_id integer, price double precision, release_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, sm.service_contract_id, sm.activity_id, sm.price, sm.release_date
FROM tourist_package_modality AS tpm INNER JOIN service_modality AS sm
ON tpm.modality_id = sm.modality_id INNER JOIN modality AS m ON sm.modality_id = m.id
WHERE tpm.tourist_package_id = _id_tourist_package;
END;
$$;
 X   DROP FUNCTION public.get_service_modality_tourist_package(_id_tourist_package integer);
       public          postgres    false            7           1255    116136    get_service_provider(integer)    FUNCTION     k  CREATE FUNCTION public.get_service_provider(_id_service_provider integer) RETURNS TABLE(id integer, service_name character varying, province character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select s.id, p.name, p.province from service_provider AS s INNER JOIN provider AS p 
ON s.id = p.id_provider where s.id = _id_service_provider;
END;
$$;
 I   DROP FUNCTION public.get_service_provider(_id_service_provider integer);
       public          postgres    false            &           1255    116137    get_tourist_package(integer)    FUNCTION     �  CREATE FUNCTION public.get_tourist_package(_id_tourist_package integer) RETURNS TABLE(id_tourist_package integer, name character varying, cant_max_pax integer, cant_reserves integer, start_date date, termination_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT tp.id_tourist_package, tp.name, tp.cant_max_pax, tp.cant_reserves, tp.start_date, tp.termination_date  
FROM tourist_package AS tp
WHERE tp.id_tourist_package = _id_tourist_package;
END;
$$;
 G   DROP FUNCTION public.get_tourist_package(_id_tourist_package integer);
       public          postgres    false            �           1255    116138 /   get_transport_modality_cost_kilometers(integer)    FUNCTION     �  CREATE FUNCTION public.get_transport_modality_cost_kilometers(_id_modality integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_going double precision, cost_kilometers_lap double precision, cost_hours_wait double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, ck.cost_kilometers_going, ck.cost_kilometers_lap, 
ck.cost_hours_wait FROM cost_kilometers AS ck
INNER JOIN transport_modality AS tm 
ON ck.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE m.id = _id_modality;
END;
$$;
 S   DROP FUNCTION public.get_transport_modality_cost_kilometers(_id_modality integer);
       public          postgres    false            �           1255    116139 ?   get_transport_modality_cost_kilometers_tourist_package(integer)    FUNCTION     Y  CREATE FUNCTION public.get_transport_modality_cost_kilometers_tourist_package(_id_tourist_package integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_going double precision, cost_kilometers_lap double precision, cost_hours_wait double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, ck.cost_kilometers_going, ck.cost_kilometers_lap, 
ck.cost_hours_wait FROM tourist_package_modality AS tpm INNER JOIN cost_kilometers AS ck
ON tpm.modality_id = ck.modality_id INNER JOIN transport_modality AS tm 
ON ck.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE tpm.tourist_package_id = _id_tourist_package;
END;
$$;
 j   DROP FUNCTION public.get_transport_modality_cost_kilometers_tourist_package(_id_tourist_package integer);
       public          postgres    false            %           1255    116140 1   get_transport_modality_established_route(integer)    FUNCTION     �  CREATE FUNCTION public.get_transport_modality_established_route(_id_modality integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, description_rout character varying, cost_going double precision, cost_lap double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, er.description_rout, er.cost_going, 
er.cost_lap FROM established_route AS er
INNER JOIN transport_modality AS tm 
ON er.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE m.id = _id_modality;
END;
$$;
 U   DROP FUNCTION public.get_transport_modality_established_route(_id_modality integer);
       public          postgres    false            Z           1255    116141 A   get_transport_modality_established_route_tourist_package(integer)    FUNCTION     4  CREATE FUNCTION public.get_transport_modality_established_route_tourist_package(_id_tourist_package integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, description_rout character varying, cost_going double precision, cost_lap double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, er.description_rout, er.cost_going, 
er.cost_lap FROM tourist_package_modality AS tpm INNER JOIN established_route AS er
ON tpm.modality_id = er.modality_id INNER JOIN transport_modality AS tm 
ON er.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE tpm.tourist_package_id = _id_tourist_package;
END;
$$;
 l   DROP FUNCTION public.get_transport_modality_established_route_tourist_package(_id_tourist_package integer);
       public          postgres    false            �           1255    116142 0   get_transport_modality_hours_kilometers(integer)    FUNCTION     -  CREATE FUNCTION public.get_transport_modality_hours_kilometers(_id_modality integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_rout double precision, cost_hours double precision, cost_kilometers_rout_additionals double precision, cost_hours_additionals double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, hk.cost_kilometers_rout, hk.cost_hours, hk.cost_kilometers_rout_additionals,
hk.cost_hours_additionals FROM hours_kilometers AS hk
INNER JOIN transport_modality AS tm 
ON hk.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE m.id = _id_modality;
END;
$$;
 T   DROP FUNCTION public.get_transport_modality_hours_kilometers(_id_modality integer);
       public          postgres    false            M           1255    116143 @   get_transport_modality_hours_kilometers_tourist_package(integer)    FUNCTION     �  CREATE FUNCTION public.get_transport_modality_hours_kilometers_tourist_package(_id_tourist_package integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, carrier_contract_id integer, type_transport_modality character varying, cost_kilometers_rout double precision, cost_hours double precision, cost_kilometers_rout_additionals double precision, cost_hours_additionals double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.carrier_contract_id, 
tm.type_transport_modality, hk.cost_kilometers_rout, hk.cost_hours, hk.cost_kilometers_rout_additionals,
hk.cost_hours_additionals FROM tourist_package_modality AS tpm INNER JOIN hours_kilometers AS hk
ON tpm.modality_id = hk.modality_id INNER JOIN transport_modality AS tm 
ON hk.modality_id = tm.modality_id INNER JOIN modality AS m ON tm.modality_id = m.id
WHERE tpm.tourist_package_id = _id_tourist_package;
END;
$$;
 k   DROP FUNCTION public.get_transport_modality_hours_kilometers_tourist_package(_id_tourist_package integer);
       public          postgres    false            �           1255    116144 $   get_transportation_provider(integer)    FUNCTION     �  CREATE FUNCTION public.get_transportation_provider(_id_transportation_provider integer) RETURNS TABLE(id integer, provider_name character varying, province character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select t.id, p.name, p.province from transportation_provider AS t INNER JOIN provider AS P 
ON t.id = p.id_provider where t.id = _id_transportation_provider ;
END;
$$;
 W   DROP FUNCTION public.get_transportation_provider(_id_transportation_provider integer);
       public          postgres    false            o           1255    116145 B   get_transports_modalitys_cost_kilometers_carrier_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_transports_modalitys_cost_kilometers_carrier_contract(_id_carrier_contract integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, type_transport_modality character varying, cost_kilometers_going double precision, cost_kilometers_lap double precision, cost_hours_wait double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.type_transport_modality,
ck.cost_kilometers_going, ck.cost_kilometers_lap, ck.cost_hours_wait
FROM cost_kilometers AS ck INNER JOIN transport_modality AS tm ON ck.modality_id = tm.modality_id
INNER JOIN modality AS m ON tm.modality_id = m.id WHERE tm.carrier_contract_id = _id_carrier_contract;
END;
$$;
 n   DROP FUNCTION public.get_transports_modalitys_cost_kilometers_carrier_contract(_id_carrier_contract integer);
       public          postgres    false            �           1255    116146 D   get_transports_modalitys_established_route_carrier_contract(integer)    FUNCTION     �  CREATE FUNCTION public.get_transports_modalitys_established_route_carrier_contract(_id_carrier_contract integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, type_transport_modality character varying, description_rout character varying, cost_going double precision, cost_lap double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.type_transport_modality,
er.description_rout, er.cost_going, er.cost_lap
FROM established_route AS er INNER JOIN transport_modality AS tm ON er.modality_id = tm.modality_id
INNER JOIN modality AS m ON tm.modality_id = m.id WHERE tm.carrier_contract_id = _id_carrier_contract;
END;
$$;
 p   DROP FUNCTION public.get_transports_modalitys_established_route_carrier_contract(_id_carrier_contract integer);
       public          postgres    false            �           1255    116147 C   get_transports_modalitys_hours_kilometers_carrier_contract(integer)    FUNCTION     +  CREATE FUNCTION public.get_transports_modalitys_hours_kilometers_carrier_contract(_id_carrier_contract integer) RETURNS TABLE(modality_id integer, type_of_modality character varying, type_transport_modality character varying, cost_kilometers_rout double precision, cost_hours double precision, cost_kilometers_rout_additionals double precision, cost_hours_additionals double precision)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select m.id, m.type_of_modality, tm.type_transport_modality,
hk.cost_kilometers_rout, hk.cost_hours, hk.cost_kilometers_rout_additionals, hk.cost_hours_additionals
FROM hours_kilometers AS hk INNER JOIN transport_modality AS tm ON hk.modality_id = tm.modality_id
INNER JOIN modality AS m ON tm.modality_id = m.id WHERE tm.carrier_contract_id = _id_carrier_contract;
END;
$$;
 o   DROP FUNCTION public.get_transports_modalitys_hours_kilometers_carrier_contract(_id_carrier_contract integer);
       public          postgres    false            t           1255    116148    get_type_of_room(integer)    FUNCTION       CREATE FUNCTION public.get_type_of_room(_id_type_of_room integer) RETURNS TABLE(id integer, type_of_room_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select t.id, t.type_of_room_name FROM type_of_room AS t WHERE t.id = _id_type_of_room;
END;
$$;
 A   DROP FUNCTION public.get_type_of_room(_id_type_of_room integer);
       public          postgres    false            Y           1255    116149    get_type_of_room_hotel(integer)    FUNCTION     P  CREATE FUNCTION public.get_type_of_room_hotel(_id_hotel integer) RETURNS TABLE(id integer, type_of_room_name character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query select t.id, t.type_of_room_name from hotel_type_of_room as ht inner join type_of_room t on ht.type_of_room_id = t.id
WHERE ht.hotel_id = _id_hotel;
END;
$$;
 @   DROP FUNCTION public.get_type_of_room_hotel(_id_hotel integer);
       public          postgres    false            �           1255    116150    get_user(character varying)    FUNCTION       CREATE FUNCTION public.get_user(_user_name character varying) RETURNS TABLE(id_user integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT u.id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u WHERE u.user_name = _user_name;
END;
$$;
 =   DROP FUNCTION public.get_user(_user_name character varying);
       public          postgres    false            �           1255    116151 .   get_user(character varying, character varying)    FUNCTION     f  CREATE FUNCTION public.get_user(_user_name character varying, _password character varying) RETURNS TABLE(id_user integer, user_name character varying, user_password character varying, id_rol integer, start_date_connection date, last_date_connection date, connected boolean, state_password boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT u.id, u.user_name, u.user_password, u.id_rol, u.start_date_connection, 
u.last_date_connection, u.connected, u.state_password  
FROM public.user AS u WHERE u.user_name = _user_name AND 
PGP_SYM_DECRYPT(u.user_password::bytea, 'AES_KEY') = _password;
END;
$$;
 Z   DROP FUNCTION public.get_user(_user_name character varying, _password character varying);
       public          postgres    false            �           1255    116152    get_vehicle(integer)    FUNCTION     I  CREATE FUNCTION public.get_vehicle(_id_vehicle integer) RETURNS TABLE(id_vehicle integer, lock character varying, transportation_provider_id integer, brand character varying, capacity_without_luggage integer, capacity_with_luggage integer, date_of_production date, total_capacity integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT v.id_vehicle, v.lock, v.transportation_provider_id, v.brand,
v.capacity_without_luggage, v.capacity_with_luggage, v.capacity_with_luggage, v.date_of_production,
v.total_capacity FROM vehicle AS v
WHERE v.id_vehicle = _id_vehicle;
END;
$$;
 7   DROP FUNCTION public.get_vehicle(_id_vehicle integer);
       public          postgres    false            R           1255    116153 '   get_vehicle_transport_modality(integer)    FUNCTION     �  CREATE FUNCTION public.get_vehicle_transport_modality(_id_transport_modality integer) RETURNS TABLE(id_vehicle integer, lock character varying, transportation_provider_id integer, brand character varying, capacity_without_luggage integer, capacity_with_luggage integer, date_of_production date, total_capacity integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT v.id_vehicle, v.lock, v.transportation_provider_id, v.brand,
v.capacity_without_luggage, v.capacity_with_luggage, v.date_of_production,
v.total_capacity FROM transport_modality_vehicle AS tmv INNER JOIN  vehicle AS v 
ON tmv.vehicle_id = v.id_vehicle
WHERE tmv.transport_modality_id = _id_transport_modality;
END;
$$;
 U   DROP FUNCTION public.get_vehicle_transport_modality(_id_transport_modality integer);
       public          postgres    false            �           1255    116154 ,   get_vehicle_transportation_provider(integer)    FUNCTION     x  CREATE FUNCTION public.get_vehicle_transportation_provider(_id_transportation_provider integer) RETURNS TABLE(id_vehicle integer, lock character varying, transportation_provider_id integer, brand character varying, capacity_without_luggage integer, capacity_with_luggage integer, date_of_production date, total_capacity integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
return query SELECT v.id_vehicle, v.lock, v.transportation_provider_id, v.brand,
v.capacity_without_luggage, v.capacity_with_luggage, v.date_of_production,
v.total_capacity FROM vehicle AS v
WHERE v.transportation_provider_id = _id_transportation_provider;
END;
$$;
 _   DROP FUNCTION public.get_vehicle_transportation_provider(_id_transportation_provider integer);
       public          postgres    false            >           1255    116155 j   insert_accommodation_contract(date, date, character varying, character varying, integer, double precision)    FUNCTION     �  CREATE FUNCTION public.insert_accommodation_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _hotel_id integer, _surcharge double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_contract INTEGER;

BEGIN
INSERT INTO contract (contract_start_date, contract_termination_date, contract_description, type_of_contract, surcharge, state)
VALUES (_contract_strart_date, _contract_termination_date, _contract_description, _type_of_contract, _surcharge, false)
RETURNING id_contract INTO _id_contract;


INSERT INTO accommodation_contract (id_contract, hotel_id)
VALUES (_id_contract, _hotel_id);

RETURN _id_contract;
END;
$$;
 �   DROP FUNCTION public.insert_accommodation_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _hotel_id integer, _surcharge double precision);
       public          postgres    false            �           1255    116156 o   insert_accommodation_modality(character varying, integer, integer, integer, double precision, integer, integer)    FUNCTION     �  CREATE FUNCTION public.insert_accommodation_modality(_type_of_modality character varying, _accommodation_contract_id integer, _type_of_room_id integer, _meal_plan_id integer, _price double precision, _cant_days_accommodation integer, _hotel_modality_id integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_modality INTEGER;
BEGIN

INSERT INTO modality (type_of_modality) VALUES (_type_of_modality) RETURNING id INTO _id_modality;


INSERT INTO accommodation_modality (id ,accommodation_contract_id, type_of_room_id, meal_plan_id, price, cant_days_accommodation, hotel_modality_id)
VALUES (_id_modality ,_accommodation_contract_id, _type_of_room_id, _meal_plan_id, _price, _cant_days_accommodation, _hotel_modality_id);

RETURN _id_modality;
END;
$$;
   DROP FUNCTION public.insert_accommodation_modality(_type_of_modality character varying, _accommodation_contract_id integer, _type_of_room_id integer, _meal_plan_id integer, _price double precision, _cant_days_accommodation integer, _hotel_modality_id integer);
       public          postgres    false            *           1255    116157 >   insert_activity(character varying, integer, character varying)    FUNCTION     �  CREATE FUNCTION public.insert_activity(_activity_description character varying, _service_provider_id integer, _name character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_activity integer;
BEGIN
 INSERT INTO activity (activity_description, service_provider_id, name) 
 VALUES (_activity_description, _service_provider_id, _name) RETURNING id_activity INTO _id_activity;
 
 RETURN _id_activity;
END;
$$;
 �   DROP FUNCTION public.insert_activity(_activity_description character varying, _service_provider_id integer, _name character varying);
       public          postgres    false            �           1255    116158 C   insert_administrator(character varying, character varying, integer)    FUNCTION     �  CREATE FUNCTION public.insert_administrator(_user_name character varying, _user_password character varying, _id_rol integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_user_id integer;
BEGIN

INSERT INTO public.user (user_name, user_password, id_rol, connected, state_password) 
VALUES (_user_name, PGP_SYM_ENCRYPT(_user_password,'AES_KEY'), _id_rol, false, false) RETURNING id INTO _user_id;

INSERT INTO public.administrator (user_id)
VALUES (_user_id);

RETURN _user_id;
END;
$$;
 |   DROP FUNCTION public.insert_administrator(_user_name character varying, _user_password character varying, _id_rol integer);
       public          postgres    false            �           1255    116159 d   insert_carrier_contract(date, date, character varying, character varying, integer, double precision)    FUNCTION       CREATE FUNCTION public.insert_carrier_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _transportation_provider_id integer, _surcharge double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_contract INTEGER;

BEGIN
INSERT INTO contract (contract_start_date, contract_termination_date, contract_description, type_of_contract, surcharge, state)
VALUES (_contract_strart_date, _contract_termination_date, _contract_description, _type_of_contract, _surcharge, false)
RETURNING id_contract INTO _id_contract  ;

INSERT INTO carrier_contract (id_contract, transportation_provider_id)
VALUES (_id_contract, _transportation_provider_id);

RETURN _id_contract;
END;
$$;
 �   DROP FUNCTION public.insert_carrier_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _transportation_provider_id integer, _surcharge double precision);
       public          postgres    false                       1255    116160 ?   insert_dependent(character varying, character varying, integer)    FUNCTION     �  CREATE FUNCTION public.insert_dependent(_user_name character varying, _user_password character varying, _id_rol integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_user_id integer;
BEGIN

INSERT INTO public.user (user_name, user_password, id_rol, connected, state_password) 
VALUES (_user_name, PGP_SYM_ENCRYPT(_user_password,'AES_KEY'), _id_rol, false, false) RETURNING id INTO _user_id;

INSERT INTO public.dependent (user_id)
VALUES (_user_id);

RETURN _user_id;
END;
$$;
 x   DROP FUNCTION public.insert_dependent(_user_name character varying, _user_password character varying, _id_rol integer);
       public          postgres    false            P           1255    116161 �   insert_hotel(character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, integer, integer, character varying, double precision, double precision, date)    FUNCTION     �  CREATE FUNCTION public.insert_hotel(_name character varying, _hotel_chain character varying, _province character varying, _address character varying, _hotel_category integer, _phone integer, _fax character varying, _email character varying, _cant_rooms integer, _cant_floors integer, _location_hotel character varying, _distance_nearest_city double precision, _distance_airport double precision, _date_built date) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_provider integer;
BEGIN

INSERT INTO provider (name, province) VALUES (_name, _province) RETURNING id_provider INTO _id_provider ;

INSERT INTO hotel (id, hotel_chain, address, hotel_category, phone, fax, email, cant_rooms, cant_floors,
				  location_hotel, distance_nearest_city, distance_airport, date_built) 
VALUES (_id_provider, _hotel_chain, _address, _hotel_category, _phone, _fax, _email, _cant_rooms, _cant_floors,
	   _location_hotel, _distance_nearest_city, _distance_airport, _date_built);

RETURN _id_provider;
END;
$$;
 �  DROP FUNCTION public.insert_hotel(_name character varying, _hotel_chain character varying, _province character varying, _address character varying, _hotel_category integer, _phone integer, _fax character varying, _email character varying, _cant_rooms integer, _cant_floors integer, _location_hotel character varying, _distance_nearest_city double precision, _distance_airport double precision, _date_built date);
       public          postgres    false            c           1255    116162 (   insert_hotel_modality(character varying)    FUNCTION       CREATE FUNCTION public.insert_hotel_modality(_name character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
_id_hotel_modality INTEGER;

BEGIN

INSERT INTO hotel_modality (name) VALUES (_name) RETURNING id 
INTO _id_hotel_modality;

RETURN _id_hotel_modality;

END;$$;
 E   DROP FUNCTION public.insert_hotel_modality(_name character varying);
       public          postgres    false            �           1255    116163 2   insert_hotel_modality_into_hotel(integer, integer)    FUNCTION     	  CREATE FUNCTION public.insert_hotel_modality_into_hotel(_hotel_id integer, _hotel_modality_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO hotel_hotel_modality (hotel_id, hotel_modality_id)
VALUES (_hotel_id, _hotel_modality_id);
END;
$$;
 f   DROP FUNCTION public.insert_hotel_modality_into_hotel(_hotel_id integer, _hotel_modality_id integer);
       public          postgres    false            '           1255    116164 =   insert_manager(character varying, character varying, integer)    FUNCTION     �  CREATE FUNCTION public.insert_manager(_user_name character varying, _user_password character varying, _id_rol integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_user_id integer;
BEGIN

INSERT INTO public.user (user_name, user_password, id_rol, connected, state_password) 
VALUES (_user_name, PGP_SYM_ENCRYPT(_user_password,'AES_KEY'), _id_rol, false , false) RETURNING id INTO _user_id;

INSERT INTO public.manager (user_id)
VALUES (_user_id);

RETURN _user_id;
END;
$$;
 v   DROP FUNCTION public.insert_manager(_user_name character varying, _user_password character varying, _id_rol integer);
       public          postgres    false            �           1255    116165 #   insert_meal_plan(character varying)    FUNCTION     #  CREATE FUNCTION public.insert_meal_plan(_meal_plan_name character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_meal_plan integer;
BEGIN
INSERT INTO meal_plan (meal_plan_name) 
VALUES (_meal_plan_name) RETURNING id INTO _id_meal_plan ;

RETURN _id_meal_plan;

END;
$$;
 J   DROP FUNCTION public.insert_meal_plan(_meal_plan_name character varying);
       public          postgres    false            W           1255    116166 (   insert_meal_plan_hotel(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_meal_plan_hotel(_hotel_id integer, _meal_plan_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
INSERT INTO hotel_meal_plan (hotel_id, meal_plan_id)
VALUES (_hotel_id, _meal_plan_id);
END;$$;
 W   DROP FUNCTION public.insert_meal_plan_hotel(_hotel_id integer, _meal_plan_id integer);
       public          postgres    false            #           1255    116167 1   insert_modality_tourist_package(integer, integer)    FUNCTION       CREATE FUNCTION public.insert_modality_tourist_package(_modality_id integer, _tourist_package_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
INSERT INTO tourist_package_modality (modality_id, tourist_package_id)
VALUES (_modality_id, _tourist_package_id);
END;$$;
 i   DROP FUNCTION public.insert_modality_tourist_package(_modality_id integer, _tourist_package_id integer);
       public          postgres    false            n           1255    116168 F   insert_package_designer(character varying, character varying, integer)    FUNCTION     �  CREATE FUNCTION public.insert_package_designer(_user_name character varying, _user_password character varying, _id_rol integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_user_id integer;
BEGIN

INSERT INTO public.user (user_name, user_password, id_rol, connected, state_password) 
VALUES (_user_name, PGP_SYM_ENCRYPT(_user_password,'AES_KEY'), _id_rol, false, false) RETURNING id INTO _user_id;

INSERT INTO public.package_designer (user_id)
VALUES (_user_id);

RETURN _user_id;
END;
$$;
    DROP FUNCTION public.insert_package_designer(_user_name character varying, _user_password character varying, _id_rol integer);
       public          postgres    false            B           1255    116169 @   insert_password_change_request(character varying, date, integer)    FUNCTION     �  CREATE FUNCTION public.insert_password_change_request(_type_request character varying, _date_request date, _user_id integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_request INTEGER;
BEGIN
INSERT INTO public.request (type_request, date_request, user_id) VALUES (_type_request, _date_request, _user_id)
RETURNING id INTO _id_request;


INSERT INTO public.password_change_request (id) VALUES (_id_request);


RETURN _id_request;
END;
$$;
 |   DROP FUNCTION public.insert_password_change_request(_type_request character varying, _date_request date, _user_id integer);
       public          postgres    false            �           1255    116170    insert_rol(character varying)    FUNCTION     �   CREATE FUNCTION public.insert_rol(_rol_name character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
_id_rol integer;
BEGIN
INSERT INTO public.rol (rol_name) VALUES (_rol_name) RETURNING id INTO _id_rol;

RETURN _id_rol;
END;$$;
 >   DROP FUNCTION public.insert_rol(_rol_name character varying);
       public          postgres    false            F           1255    116171 [   insert_season(character varying, date, date, character varying, character varying, integer)    FUNCTION     �  CREATE FUNCTION public.insert_season(_season_name character varying, _season_start_date date, _season_termination_date date, _season_description character varying, _type_season character varying, _accommodation_contract_id integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_season integer;
BEGIN
INSERT INTO season (season_name, season_start_date, season_termination_date, season_description, type_of_season, accommodation_contract_id) 
VALUES (_season_name, _season_start_date, _season_termination_date, _season_description, _type_season, _accommodation_contract_id)
RETURNING id INTO _id_season ;

RETURN _id_season;
END;
$$;
 �   DROP FUNCTION public.insert_season(_season_name character varying, _season_start_date date, _season_termination_date date, _season_description character varying, _type_season character varying, _accommodation_contract_id integer);
       public          postgres    false            �           1255    116172 d   insert_service_contract(date, date, character varying, character varying, integer, double precision)    FUNCTION     �  CREATE FUNCTION public.insert_service_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _service_provider_id integer, _surcharge double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_contract INTEGER;

BEGIN
INSERT INTO contract (contract_start_date, contract_termination_date, contract_description, type_of_contract, surcharge, state) 
VALUES (_contract_strart_date, _contract_termination_date, _contract_description, _type_of_contract, _surcharge, false) 
RETURNING id_contract INTO _id_contract;

INSERT INTO service_contract (id_contract, service_provider_id) 
VALUES (_id_contract, _service_provider_id);

RETURN _id_contract;

END;
$$;
 �   DROP FUNCTION public.insert_service_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _type_of_contract character varying, _service_provider_id integer, _surcharge double precision);
       public          postgres    false            �           1255    116173 T   insert_service_modality(character varying, integer, integer, date, double precision)    FUNCTION     o  CREATE FUNCTION public.insert_service_modality(_type_of_modality character varying, _service_contract_id integer, _activity_id integer, _release_date date, _price double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_modality INTEGER;
BEGIN
INSERT INTO modality (type_of_modality) 
VALUES (_type_of_modality);

SELECT currval(pg_get_serial_sequence('modality', 'id')) INTO _id_modality;

INSERT INTO service_modality (modality_id, service_contract_id, activity_id, price, release_date) 
VALUES (_id_modality, _service_contract_id, _activity_id, _price, _release_date);

RETURN _id_modality;
END;
$$;
 �   DROP FUNCTION public.insert_service_modality(_type_of_modality character varying, _service_contract_id integer, _activity_id integer, _release_date date, _price double precision);
       public          postgres    false            V           1255    116174 =   insert_service_provider(character varying, character varying)    FUNCTION     ~  CREATE FUNCTION public.insert_service_provider(_name character varying, _province character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_provider integer;
BEGIN

INSERT INTO provider (name, province) VALUES (_name, _province) RETURNING id_provider INTO _id_provider  ;


INSERT INTO service_provider (id)
values (_id_provider);

RETURN _id_provider;
END;
$$;
 d   DROP FUNCTION public.insert_service_provider(_name character varying, _province character varying);
       public          postgres    false            �           1255    116175 G   insert_tourist_package(character varying, integer, integer, date, date)    FUNCTION       CREATE FUNCTION public.insert_tourist_package(_name character varying, _cant_max_pax integer, _cant_reserves integer, _start_date date, _termination_date date) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_tourist_package integer;
BEGIN
INSERT INTO tourist_package (name, cant_max_pax, cant_reserves, start_date, termination_date) 
VALUES (_name, _cant_max_pax, _cant_reserves, _start_date, _termination_date) RETURNING id_tourist_package INTO _id_tourist_package;

RETURN _id_tourist_package;
END;
$$;
 �   DROP FUNCTION public.insert_tourist_package(_name character varying, _cant_max_pax integer, _cant_reserves integer, _start_date date, _termination_date date);
       public          postgres    false            �           1255    116176 �   insert_transport_modality_cost_kilometers(character varying, integer, character varying, double precision, double precision, double precision)    FUNCTION     [  CREATE FUNCTION public.insert_transport_modality_cost_kilometers(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _cost_kilometers_going double precision, _cost_kilometers_lap double precision, _cost_hours_wait double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_modality_id INTEGER;
BEGIN
INSERT INTO modality (type_of_modality)
VALUES (_type_of_modality) RETURNING id INTO _modality_id;


INSERT INTO transport_modality (modality_id, carrier_contract_id, type_transport_modality)
VALUES (_modality_id, _carrier_contract_id, _type_transport_modality);

INSERT INTO cost_kilometers (modality_id, cost_kilometers_going, cost_kilometers_lap, cost_hours_wait) 
VALUES (_modality_id, _cost_kilometers_going, _cost_kilometers_lap, _cost_hours_wait);
RETURN _modality_id;
END;
$$;
 "  DROP FUNCTION public.insert_transport_modality_cost_kilometers(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _cost_kilometers_going double precision, _cost_kilometers_lap double precision, _cost_hours_wait double precision);
       public          postgres    false            �           1255    116177 �   insert_transport_modality_established_route(character varying, integer, character varying, character varying, double precision, double precision)    FUNCTION     !  CREATE FUNCTION public.insert_transport_modality_established_route(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _description_rout character varying, _cost_going double precision, _cost_lap double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_modality_id INTEGER;
BEGIN
INSERT INTO modality (type_of_modality)
VALUES (_type_of_modality) RETURNING id INTO _modality_id;


INSERT INTO transport_modality (modality_id, carrier_contract_id, type_transport_modality)
VALUES (_modality_id, _carrier_contract_id, _type_transport_modality);

INSERT INTO established_route (modality_id, description_rout, cost_going, cost_lap) 
VALUES (_modality_id, _description_rout, _cost_going, _cost_lap);
RETURN _modality_id;
END;
$$;
   DROP FUNCTION public.insert_transport_modality_established_route(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _description_rout character varying, _cost_going double precision, _cost_lap double precision);
       public          postgres    false            6           1255    116178 �   insert_transport_modality_hours_kilometers(character varying, integer, character varying, double precision, double precision, double precision, double precision)    FUNCTION     �  CREATE FUNCTION public.insert_transport_modality_hours_kilometers(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _cost_kilometers_rout double precision, _cost_hours double precision, _cost_kilometers_rout_addtionals double precision, _cost_hours_additionals double precision) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_modality_id INTEGER;
BEGIN
INSERT INTO modality (type_of_modality)
VALUES (_type_of_modality) RETURNING id INTO _modality_id;


INSERT INTO transport_modality (modality_id, carrier_contract_id, type_transport_modality)
VALUES (_modality_id, _carrier_contract_id, _type_transport_modality);

INSERT INTO hours_kilometers (modality_id, cost_kilometers_rout, cost_hours, cost_kilometers_rout_additionals, cost_hours_additionals) 
VALUES (_modality_id, _cost_kilometers_rout, _cost_hours, _cost_kilometers_rout_addtionals, _cost_hours_additionals);

RETURN _modality_id;
END;
$$;
 S  DROP FUNCTION public.insert_transport_modality_hours_kilometers(_type_of_modality character varying, _carrier_contract_id integer, _type_transport_modality character varying, _cost_kilometers_rout double precision, _cost_hours double precision, _cost_kilometers_rout_addtionals double precision, _cost_hours_additionals double precision);
       public          postgres    false            s           1255    116179 D   insert_transportation_provider(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.insert_transportation_provider(_name character varying, _province character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_provider integer;
BEGIN
INSERT INTO provider (name, province) VALUES (_name, _province) RETURNING id_provider INTO _id_provider;

INSERT INTO  transportation_provider (id) values (_id_provider);

RETURN _id_provider;
END;
$$;
 k   DROP FUNCTION public.insert_transportation_provider(_name character varying, _province character varying);
       public          postgres    false            �           1255    116180 &   insert_type_of_room(character varying)    FUNCTION     :  CREATE FUNCTION public.insert_type_of_room(_type_of_room_name character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_type_of_room integer;
BEGIN
INSERT INTO type_of_room (type_of_room_name) 
VALUES (_type_of_room_name) RETURNING id INTO _id_type_of_room ;

RETURN _id_type_of_room;
END;
$$;
 P   DROP FUNCTION public.insert_type_of_room(_type_of_room_name character varying);
       public          postgres    false            ;           1255    116181 +   insert_type_of_room_hotel(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_type_of_room_hotel(_hotel_id integer, _type_of_room_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
INSERT INTO hotel_type_of_room (hotel_id, type_of_room_id)
VALUES (_hotel_id, _type_of_room_id);
END;$$;
 ]   DROP FUNCTION public.insert_type_of_room_hotel(_hotel_id integer, _type_of_room_id integer);
       public          postgres    false            �           1255    116182 8   insert_vehicle_into_transport_modality(integer, integer)    FUNCTION     (  CREATE FUNCTION public.insert_vehicle_into_transport_modality(_id_transport_modality integer, _id_vehicle integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN
INSERT INTO transport_modality_vehicle (transport_modality_id, vehicle_id)
VALUES (_id_transport_modality, _id_vehicle);
END;
$$;
 r   DROP FUNCTION public.insert_vehicle_into_transport_modality(_id_transport_modality integer, _id_vehicle integer);
       public          postgres    false            �           1255    116183 {   insert_vehicle_into_transportation_provider(character varying, integer, character varying, integer, integer, date, integer)    FUNCTION     �  CREATE FUNCTION public.insert_vehicle_into_transportation_provider(_lock character varying, _transportation_provider_id integer, _brand character varying, _capacity_without_luggage integer, _capacity_with_luggage integer, _date_of_production date, _total_capacity integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
_id_vehicle integer;
BEGIN
INSERT INTO vehicle (lock, transportation_provider_id, brand, capacity_without_luggage, capacity_with_luggage,
					date_of_production, total_capacity) 
VALUES (_lock, _transportation_provider_id, _brand, _capacity_without_luggage, _capacity_with_luggage,
	   _date_of_production, _total_capacity) RETURNING id_vehicle INTO _id_vehicle;

RETURN _id_vehicle;
END;
$$;
   DROP FUNCTION public.insert_vehicle_into_transportation_provider(_lock character varying, _transportation_provider_id integer, _brand character varying, _capacity_without_luggage integer, _capacity_with_luggage integer, _date_of_production date, _total_capacity integer);
       public          postgres    false            �           1255    116184 W   update_accommodation_contract(date, date, character varying, integer, double precision)    FUNCTION     �  CREATE FUNCTION public.update_accommodation_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE contract SET contract_start_date = _contract_strart_date,
contract_termination_date = _contract_termination_date, contract_description = _contract_description,
surcharge = _surcharge
WHERE id_contract = _id_contract;

END;
$$;
 �   DROP FUNCTION public.update_accommodation_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision);
       public          postgres    false            �           1255    116185 \   update_accommodation_modality(integer, integer, integer, double precision, integer, integer)    FUNCTION       CREATE FUNCTION public.update_accommodation_modality(_id_accommodation_modality integer, _type_of_room_id integer, _meal_plan_id integer, _price double precision, _cant_days_accommodation integer, _hotel_modality_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN
 UPDATE accommodation_modality SET type_of_room_id = _type_of_room_id, meal_plan_id = _meal_plan_id,
 price = _price, cant_days_accommodation = _cant_days_accommodation, hotel_modality_id = _hotel_modality_id 
 WHERE id = _id_accommodation_modality;
END;
$$;
 �   DROP FUNCTION public.update_accommodation_modality(_id_accommodation_modality integer, _type_of_room_id integer, _meal_plan_id integer, _price double precision, _cant_days_accommodation integer, _hotel_modality_id integer);
       public          postgres    false            U           1255    116186 +   update_activity(integer, character varying)    FUNCTION       CREATE FUNCTION public.update_activity(_id_activity integer, _activity_description character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE activity SET activity_description = _activity_description
WHERE id_activity = _id_activity;
END;
$$;
 e   DROP FUNCTION public.update_activity(_id_activity integer, _activity_description character varying);
       public          postgres    false            �           1255    116187 C   update_administrator(integer, character varying, character varying)    FUNCTION     0  CREATE FUNCTION public.update_administrator(_id_administrator integer, _user_name character varying, _user_password character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password
WHERE id = _id_administrator;

END;
$$;
 �   DROP FUNCTION public.update_administrator(_id_administrator integer, _user_name character varying, _user_password character varying);
       public          postgres    false            �           1255    116188 X   update_administrator(integer, character varying, character varying, date, date, boolean)    FUNCTION     �  CREATE FUNCTION public.update_administrator(_id_administrator integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password,
start_date_connection = _start_date_connection, last_date_connection = _last_date_connection, connected = _connected
WHERE id = _id_administrator;

END;
$$;
 �   DROP FUNCTION public.update_administrator(_id_administrator integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean);
       public          postgres    false            �           1255    116189 Q   update_carrier_contract(date, date, character varying, integer, double precision)    FUNCTION     �  CREATE FUNCTION public.update_carrier_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE contract SET contract_start_date = _contract_strart_date,
contract_termination_date = _contract_termination_date, contract_description = _contract_description, 
surcharge = _surcharge
WHERE id_contract = _id_contract;
END;
$$;
 �   DROP FUNCTION public.update_carrier_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision);
       public          postgres    false            �           1255    116190 ?   update_dependent(integer, character varying, character varying)    FUNCTION        CREATE FUNCTION public.update_dependent(_id_manager integer, _user_name character varying, _user_password character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password
WHERE id = _id_manager;

END;
$$;
 |   DROP FUNCTION public.update_dependent(_id_manager integer, _user_name character varying, _user_password character varying);
       public          postgres    false            3           1255    116191 T   update_dependent(integer, character varying, character varying, date, date, boolean)    FUNCTION     �  CREATE FUNCTION public.update_dependent(_id_manager integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password,
start_date_connection = _start_date_connection, last_date_connection = _last_date_connection, connected = _connected
WHERE id = _id_manager;

END;
$$;
 �   DROP FUNCTION public.update_dependent(_id_manager integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean);
       public          postgres    false            _           1255    116192 �   update_hotel(integer, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, integer, integer, character varying, double precision, double precision, date)    FUNCTION     �  CREATE FUNCTION public.update_hotel(_id_hotel integer, _name character varying, _hotel_chain character varying, _province character varying, _address character varying, _hotel_category integer, _phone integer, _fax character varying, _email character varying, _cant_rooms integer, _cant_floors integer, _location_hotel character varying, _distance_nearest_city double precision, _distance_airport double precision, _date_built date) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN

UPDATE provider SET name = _name, province = _province
WHERE id_provider = _id_hotel;

UPDATE hotel SET hotel_chain = _hotel_chain, address = _address, hotel_category = _hotel_category, phone = _phone,
fax = _fax, email = _email, cant_rooms = _cant_rooms, cant_floors = _cant_floors, location_hotel = _location_hotel,
distance_nearest_city = _distance_nearest_city, distance_airport = _distance_airport, date_built = _date_built 
WHERE id = _id_hotel;

END;
$$;
 �  DROP FUNCTION public.update_hotel(_id_hotel integer, _name character varying, _hotel_chain character varying, _province character varying, _address character varying, _hotel_category integer, _phone integer, _fax character varying, _email character varying, _cant_rooms integer, _cant_floors integer, _location_hotel character varying, _distance_nearest_city double precision, _distance_airport double precision, _date_built date);
       public          postgres    false            2           1255    116193 1   update_hotel_modality(integer, character varying)    FUNCTION     �   CREATE FUNCTION public.update_hotel_modality(_id_hotel_modality integer, _name character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE hotel_modality SET name = _name WHERE id = _id_hotel_modality;

END;
$$;
 a   DROP FUNCTION public.update_hotel_modality(_id_hotel_modality integer, _name character varying);
       public          postgres    false            {           1255    116194 =   update_manager(integer, character varying, character varying)    FUNCTION       CREATE FUNCTION public.update_manager(_id_manager integer, _user_name character varying, _user_password character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password
WHERE id = _id_manager;

END;
$$;
 z   DROP FUNCTION public.update_manager(_id_manager integer, _user_name character varying, _user_password character varying);
       public          postgres    false            �           1255    116195 R   update_manager(integer, character varying, character varying, date, date, boolean)    FUNCTION     �  CREATE FUNCTION public.update_manager(_id_manager integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password,
start_date_connection = _start_date_connection, last_date_connection = _last_date_connection, connected = _connected
WHERE id = _id_manager;

END;
$$;
 �   DROP FUNCTION public.update_manager(_id_manager integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean);
       public          postgres    false            u           1255    116196 ,   update_meal_plan(integer, character varying)    FUNCTION     �   CREATE FUNCTION public.update_meal_plan(_id_meal_plan integer, _meal_plan_name character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE meal_plan SET meal_plan_name = _meal_plan_name
WHERE id = _id_meal_plan;
END;$$;
 a   DROP FUNCTION public.update_meal_plan(_id_meal_plan integer, _meal_plan_name character varying);
       public          postgres    false            �           1255    116197 F   update_package_designer(integer, character varying, character varying)    FUNCTION     9  CREATE FUNCTION public.update_package_designer(_id_package_designer integer, _user_name character varying, _user_password character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password
WHERE id = _id_package_designer;

END;
$$;
 �   DROP FUNCTION public.update_package_designer(_id_package_designer integer, _user_name character varying, _user_password character varying);
       public          postgres    false            �           1255    116198 [   update_package_designer(integer, character varying, character varying, date, date, boolean)    FUNCTION     �  CREATE FUNCTION public.update_package_designer(_id_package_designer integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean) RETURNS void
    LANGUAGE plpgsql
    AS $$

BEGIN

UPDATE public.user SET user_name = _user_name, user_password =_user_password,
start_date_connection = _start_date_connection, last_date_connection = _last_date_connection, connected = _connected
WHERE id = _id_package_designer;

END;
$$;
 �   DROP FUNCTION public.update_package_designer(_id_package_designer integer, _user_name character varying, _user_password character varying, _start_date_connection date, _last_date_connection date, _connected boolean);
       public          postgres    false            �           1255    116199 &   update_rol(integer, character varying)    FUNCTION     �   CREATE FUNCTION public.update_rol(_id_rol integer, _rol_name character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE public.rol SET rol_name = _rol_name WHERE id = _id_rol;
END;$$;
 O   DROP FUNCTION public.update_rol(_id_rol integer, _rol_name character varying);
       public          postgres    false            i           1255    116200 [   update_season(character varying, date, date, character varying, character varying, integer)    FUNCTION       CREATE FUNCTION public.update_season(_season_name character varying, _season_strart_date date, _season_termination_date date, _season_description character varying, _type_of_season character varying, _id_season integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE season SET season_name =_season_name, season_start_date = _season_strart_date,
season_termination_date = _season_termination_date, season_description = _season_description,
type_of_season = _type_of_season WHERE id = _id_season;
END;
$$;
 �   DROP FUNCTION public.update_season(_season_name character varying, _season_strart_date date, _season_termination_date date, _season_description character varying, _type_of_season character varying, _id_season integer);
       public          postgres    false            �           1255    116201 9   update_selected_plans(integer, integer, integer, integer)    FUNCTION     N  CREATE FUNCTION public.update_selected_plans(_id_selected_plans integer, _type_of_room_id integer, _meal_plan_id integer, _price integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE selected_plans SET type_of_room_id = _type_of_room_id, meal_plan_id = _meal_plan_id, price = _price 
WHERE id = _id_selected_plans;
END;$$;
 �   DROP FUNCTION public.update_selected_plans(_id_selected_plans integer, _type_of_room_id integer, _meal_plan_id integer, _price integer);
       public          postgres    false            +           1255    116202 Q   update_service_contract(date, date, character varying, integer, double precision)    FUNCTION     �  CREATE FUNCTION public.update_service_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE contract SET contract_start_date = _contract_strart_date,
contract_termination_date = _contract_termination_date, contract_description = _contract_description,
surcharge = _surcharge
WHERE id_contract = _id_contract;
END;
$$;
 �   DROP FUNCTION public.update_service_contract(_contract_strart_date date, _contract_termination_date date, _contract_description character varying, _id_contract integer, _surcharge double precision);
       public          postgres    false            �           1255    116203 A   update_service_modality(integer, integer, date, double precision)    FUNCTION     Z  CREATE FUNCTION public.update_service_modality(_id_service_modality integer, _id_activity integer, _release_date date, _price double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE service_modality SET activity_id = _id_activity, release_date = _release_date, price = _price
WHERE modality_id = _id_service_modality;
END;
$$;
 �   DROP FUNCTION public.update_service_modality(_id_service_modality integer, _id_activity integer, _release_date date, _price double precision);
       public          postgres    false            b           1255    116204 F   update_service_provider(integer, character varying, character varying)    FUNCTION     1  CREATE FUNCTION public.update_service_provider(_id_service_provider integer, _service_name character varying, _province character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN

UPDATE provider SET name = _service_name, province = _province
WHERE id_provider = _id_service_provider;

END;
$$;
 �   DROP FUNCTION public.update_service_provider(_id_service_provider integer, _service_name character varying, _province character varying);
       public          postgres    false                       1255    116205 D   update_tourist_package(integer, character varying, integer, integer)    FUNCTION     l  CREATE FUNCTION public.update_tourist_package(_id_tourist_package integer, _name character varying, _cant_max_pax integer, _cant_reserves integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$

BEGIN
UPDATE tourist_package SET name = _name, cant_max_pax = _cant_max_pax, cant_reserves = _cant_reserves   
WHERE id_tourist_package = _id_tourist_package;

END;
$$;
 �   DROP FUNCTION public.update_tourist_package(_id_tourist_package integer, _name character varying, _cant_max_pax integer, _cant_reserves integer);
       public          postgres    false            E           1255    116206 h   update_transport_modality_cost_kilometers(integer, double precision, double precision, double precision)    FUNCTION     �  CREATE FUNCTION public.update_transport_modality_cost_kilometers(_id_transport_modality integer, _cost_kilometers_going double precision, _cost_kilometers_lap double precision, _cost_hours_wait double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN

UPDATE cost_kilometers SET cost_kilometers_going = _cost_kilometers_going, cost_kilometers_lap = _cost_kilometers_lap,
cost_hours_wait = _cost_hours_wait
WHERE modality_id = _id_transport_modality;
END;
$$;
 �   DROP FUNCTION public.update_transport_modality_cost_kilometers(_id_transport_modality integer, _cost_kilometers_going double precision, _cost_kilometers_lap double precision, _cost_hours_wait double precision);
       public          postgres    false            ^           1255    116207 k   update_transport_modality_established_route(integer, character varying, double precision, double precision)    FUNCTION     �  CREATE FUNCTION public.update_transport_modality_established_route(_id_transport_modality integer, _description_rout character varying, _cost_going double precision, _cost_lap double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN

UPDATE established_route SET description_rout = _description_rout, cost_going = _cost_going,
cost_lap = _cost_lap
WHERE modality_id = _id_transport_modality;
END;
$$;
 �   DROP FUNCTION public.update_transport_modality_established_route(_id_transport_modality integer, _description_rout character varying, _cost_going double precision, _cost_lap double precision);
       public          postgres    false            g           1255    116208 {   update_transport_modality_hours_kilometers(integer, double precision, double precision, double precision, double precision)    FUNCTION     J  CREATE FUNCTION public.update_transport_modality_hours_kilometers(_id_transport_modality integer, _cost_kilometers_rout double precision, _cost_hours double precision, _cost_kilometers_rout_additionals double precision, _cost_hours_additionals double precision) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN

UPDATE hours_kilometers SET cost_kilometers_rout = _cost_kilometers_rout, cost_hours = _cost_hours,
cost_kilometers_rout_additionals = _cost_kilometers_rout_additionals, 
cost_hours_additionals = _cost_hours_additionals
WHERE modality_id = _id_transport_modality;
END;
$$;
   DROP FUNCTION public.update_transport_modality_hours_kilometers(_id_transport_modality integer, _cost_kilometers_rout double precision, _cost_hours double precision, _cost_kilometers_rout_additionals double precision, _cost_hours_additionals double precision);
       public          postgres    false            �           1255    116209 M   update_transportation_provider(integer, character varying, character varying)    FUNCTION     G  CREATE FUNCTION public.update_transportation_provider(_id_transportation_provider integer, _provider_name character varying, _province character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
UPDATE provider SET name = _provider_name, province =  _province
WHERE id_provider = _id_transportation_provider;
END;
$$;
 �   DROP FUNCTION public.update_transportation_provider(_id_transportation_provider integer, _provider_name character varying, _province character varying);
       public          postgres    false            k           1255    116210 /   update_type_of_room(integer, character varying)    FUNCTION        CREATE FUNCTION public.update_type_of_room(_id_type_of_room integer, _type_of_room_name character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE type_of_room SET type_of_room_name = _type_of_room_name
WHERE id = _id_type_of_room;
END;$$;
 j   DROP FUNCTION public.update_type_of_room(_id_type_of_room integer, _type_of_room_name character varying);
       public          postgres    false            `           1255    116211 *   update_vehicle(integer, character varying)    FUNCTION     �   CREATE FUNCTION public.update_vehicle(_id_vehicle integer, _lock character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
UPDATE vehicle SET lock = _lock WHERE id_vehicle = _id_vehicle;
END;$$;
 S   DROP FUNCTION public.update_vehicle(_id_vehicle integer, _lock character varying);
       public          postgres    false                       1255    116212 +   validar_repitencia_accommodation_modality()    FUNCTION     i  CREATE FUNCTION public.validar_repitencia_accommodation_modality() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM public.accommodation_modality as a WHERE a.type_of_room_id = NEW.type_of_room_id
			   AND a.meal_plan_id = NEW.meal_plan_id AND a.price = NEW.price AND a.cant_days_accommodation = NEW.cant_days_accommodation
			   AND a.hotel_modality_id = NEW.hotel_modality_id AND a.accommodation_contract_id = NEW.accommodation_contract_id
			   AND a.id != NEW.id) 
	THEN
        RAISE EXCEPTION 'La chapa del vehiculo ya existe en la tabla';
    END IF;
    RETURN NEW;
END;
$$;
 B   DROP FUNCTION public.validar_repitencia_accommodation_modality();
       public          postgres    false                       1255    116213 !   validar_repitencia_lock_vehicle()    FUNCTION     U  CREATE FUNCTION public.validar_repitencia_lock_vehicle() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM public.vehicle as v WHERE v.lock = NEW.lock 
			   AND v.id_vehicle != NEW.id_vehicle) 
	THEN
        RAISE EXCEPTION 'La chapa del vehiculo ya existe en la tabla';
    END IF;
    RETURN NEW;
END;
$$;
 8   DROP FUNCTION public.validar_repitencia_lock_vehicle();
       public          postgres    false            �           1255    116214 #   validar_repitencias_provider_name()    FUNCTION     Z  CREATE FUNCTION public.validar_repitencias_provider_name() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    IF EXISTS (SELECT 1 FROM public.provider as p WHERE p.name = NEW.name 
			   AND p.id_provider != NEW.id_provider) 
	THEN
        RAISE EXCEPTION 'El nombre del proveedor ya existe en la tabla';
    END IF;
    RETURN NEW;
END;$$;
 :   DROP FUNCTION public.validar_repitencias_provider_name();
       public          postgres    false            �           1255    116215    validar_repitencias_user_name()    FUNCTION     :  CREATE FUNCTION public.validar_repitencias_user_name() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM public.user as u WHERE u.user_name = NEW.user_name AND u.id != NEW.id) 
	THEN
        RAISE EXCEPTION 'El user_name ya existe en la tabla';
    END IF;
    RETURN NEW;
END;
$$;
 6   DROP FUNCTION public.validar_repitencias_user_name();
       public          postgres    false            �            1259    116216    accommodation_contract    TABLE     p   CREATE TABLE public.accommodation_contract (
    id_contract integer NOT NULL,
    hotel_id integer NOT NULL
);
 *   DROP TABLE public.accommodation_contract;
       public         heap    postgres    false            �            1259    116219    accommodation_modality    TABLE     <  CREATE TABLE public.accommodation_modality (
    id integer NOT NULL,
    accommodation_contract_id integer NOT NULL,
    type_of_room_id integer NOT NULL,
    meal_plan_id integer NOT NULL,
    price double precision NOT NULL,
    cant_days_accommodation integer NOT NULL,
    hotel_modality_id integer NOT NULL
);
 *   DROP TABLE public.accommodation_modality;
       public         heap    postgres    false            �            1259    116222    activity    TABLE     �   CREATE TABLE public.activity (
    id_activity integer NOT NULL,
    activity_description character varying(100) NOT NULL,
    service_provider_id integer NOT NULL,
    name character varying
);
    DROP TABLE public.activity;
       public         heap    postgres    false            �            1259    116227    activity_id_activity_seq    SEQUENCE     �   CREATE SEQUENCE public.activity_id_activity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.activity_id_activity_seq;
       public          postgres    false    218                       0    0    activity_id_activity_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.activity_id_activity_seq OWNED BY public.activity.id_activity;
          public          postgres    false    219            �            1259    116228    administrator    TABLE     D   CREATE TABLE public.administrator (
    user_id integer NOT NULL
);
 !   DROP TABLE public.administrator;
       public         heap    postgres    false            �            1259    116231    carrier_contract    TABLE     |   CREATE TABLE public.carrier_contract (
    id_contract integer NOT NULL,
    transportation_provider_id integer NOT NULL
);
 $   DROP TABLE public.carrier_contract;
       public         heap    postgres    false            �            1259    116234    contract    TABLE     _  CREATE TABLE public.contract (
    id_contract integer NOT NULL,
    contract_start_date date NOT NULL,
    contract_termination_date date NOT NULL,
    contract_description character varying(100) NOT NULL,
    type_of_contract character varying(45) NOT NULL,
    state boolean,
    contract_reconcilation_date date,
    surcharge double precision
);
    DROP TABLE public.contract;
       public         heap    postgres    false            �            1259    116237    contract_id_contract_seq    SEQUENCE     �   CREATE SEQUENCE public.contract_id_contract_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.contract_id_contract_seq;
       public          postgres    false    222            	           0    0    contract_id_contract_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.contract_id_contract_seq OWNED BY public.contract.id_contract;
          public          postgres    false    223            �            1259    116238    cost_kilometers    TABLE     �   CREATE TABLE public.cost_kilometers (
    modality_id integer NOT NULL,
    cost_kilometers_going double precision NOT NULL,
    cost_kilometers_lap double precision NOT NULL,
    cost_hours_wait double precision NOT NULL
);
 #   DROP TABLE public.cost_kilometers;
       public         heap    postgres    false            �            1259    116241 	   dependent    TABLE     @   CREATE TABLE public.dependent (
    user_id integer NOT NULL
);
    DROP TABLE public.dependent;
       public         heap    postgres    false            �            1259    116244    established_route    TABLE     �   CREATE TABLE public.established_route (
    modality_id integer NOT NULL,
    description_rout character varying(100) NOT NULL,
    cost_going double precision NOT NULL,
    cost_lap double precision NOT NULL
);
 %   DROP TABLE public.established_route;
       public         heap    postgres    false            �            1259    116247    hotel    TABLE       CREATE TABLE public.hotel (
    id integer NOT NULL,
    hotel_chain character varying(100) NOT NULL,
    address character varying(100) NOT NULL,
    hotel_category integer NOT NULL,
    phone integer NOT NULL,
    fax character varying,
    email character varying,
    cant_rooms integer NOT NULL,
    cant_floors integer NOT NULL,
    location_hotel character varying NOT NULL,
    distance_nearest_city double precision NOT NULL,
    distance_airport double precision NOT NULL,
    date_built date NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    116252    hotel_hotel_modality    TABLE     t   CREATE TABLE public.hotel_hotel_modality (
    hotel_id integer NOT NULL,
    hotel_modality_id integer NOT NULL
);
 (   DROP TABLE public.hotel_hotel_modality;
       public         heap    postgres    false            �            1259    116255    hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          postgres    false    227            
           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          postgres    false    229            �            1259    116256    hotel_meal_plan    TABLE     j   CREATE TABLE public.hotel_meal_plan (
    hotel_id integer NOT NULL,
    meal_plan_id integer NOT NULL
);
 #   DROP TABLE public.hotel_meal_plan;
       public         heap    postgres    false            �            1259    116259    hotel_modality_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.hotel_modality_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.hotel_modality_id_seq;
       public          postgres    false            �            1259    116260    hotel_modality    TABLE     �   CREATE TABLE public.hotel_modality (
    id integer DEFAULT nextval('public.hotel_modality_id_seq'::regclass) NOT NULL,
    name character varying NOT NULL
);
 "   DROP TABLE public.hotel_modality;
       public         heap    postgres    false    231            �            1259    116266    hotel_type_of_room    TABLE     p   CREATE TABLE public.hotel_type_of_room (
    hotel_id integer NOT NULL,
    type_of_room_id integer NOT NULL
);
 &   DROP TABLE public.hotel_type_of_room;
       public         heap    postgres    false            �            1259    116269    hours_kilometers    TABLE       CREATE TABLE public.hours_kilometers (
    modality_id integer NOT NULL,
    cost_kilometers_rout double precision NOT NULL,
    cost_hours double precision NOT NULL,
    cost_kilometers_rout_additionals double precision NOT NULL,
    cost_hours_additionals double precision NOT NULL
);
 $   DROP TABLE public.hours_kilometers;
       public         heap    postgres    false            �            1259    116272    manager    TABLE     >   CREATE TABLE public.manager (
    user_id integer NOT NULL
);
    DROP TABLE public.manager;
       public         heap    postgres    false            �            1259    116275 	   meal_plan    TABLE     o   CREATE TABLE public.meal_plan (
    id integer NOT NULL,
    meal_plan_name character varying(100) NOT NULL
);
    DROP TABLE public.meal_plan;
       public         heap    postgres    false            �            1259    116278    meal_plan_id_seq    SEQUENCE     �   CREATE SEQUENCE public.meal_plan_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.meal_plan_id_seq;
       public          postgres    false    236                       0    0    meal_plan_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.meal_plan_id_seq OWNED BY public.meal_plan.id;
          public          postgres    false    237            �            1259    116279    modality    TABLE     o   CREATE TABLE public.modality (
    id integer NOT NULL,
    type_of_modality character varying(45) NOT NULL
);
    DROP TABLE public.modality;
       public         heap    postgres    false            �            1259    116282    modality_id_seq    SEQUENCE     �   CREATE SEQUENCE public.modality_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.modality_id_seq;
       public          postgres    false    238                       0    0    modality_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.modality_id_seq OWNED BY public.modality.id;
          public          postgres    false    239            �            1259    116283    package_designer    TABLE     G   CREATE TABLE public.package_designer (
    user_id integer NOT NULL
);
 $   DROP TABLE public.package_designer;
       public         heap    postgres    false            �            1259    116286    password_change_request    TABLE     I   CREATE TABLE public.password_change_request (
    id integer NOT NULL
);
 +   DROP TABLE public.password_change_request;
       public         heap    postgres    false            �            1259    116289    provider_id_seq    SEQUENCE     x   CREATE SEQUENCE public.provider_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.provider_id_seq;
       public          postgres    false            �            1259    116290    provider    TABLE     �   CREATE TABLE public.provider (
    id_provider integer DEFAULT nextval('public.provider_id_seq'::regclass) NOT NULL,
    name character varying NOT NULL,
    province character varying NOT NULL
);
    DROP TABLE public.provider;
       public         heap    postgres    false    242            �            1259    116296    request_id_seq    SEQUENCE     w   CREATE SEQUENCE public.request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.request_id_seq;
       public          postgres    false            �            1259    116297    request    TABLE     �   CREATE TABLE public.request (
    id integer DEFAULT nextval('public.request_id_seq'::regclass) NOT NULL,
    type_request character varying NOT NULL,
    date_request date NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.request;
       public         heap    postgres    false    244            �            1259    116303 
   rol_id_seq    SEQUENCE     s   CREATE SEQUENCE public.rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.rol_id_seq;
       public          postgres    false            �            1259    116304    rol    TABLE     �   CREATE TABLE public.rol (
    id integer DEFAULT nextval('public.rol_id_seq'::regclass) NOT NULL,
    rol_name character varying NOT NULL
);
    DROP TABLE public.rol;
       public         heap    postgres    false    246            �            1259    116310    season    TABLE     T  CREATE TABLE public.season (
    id integer NOT NULL,
    season_name character varying(100) NOT NULL,
    season_start_date date NOT NULL,
    season_termination_date date NOT NULL,
    season_description character varying(200) NOT NULL,
    type_of_season character varying(45) NOT NULL,
    accommodation_contract_id integer NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    116313    season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.season_id_seq;
       public          postgres    false    248                       0    0    season_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.season_id_seq OWNED BY public.season.id;
          public          postgres    false    249            �            1259    116314    selected_plans_id_seq    SEQUENCE     �   CREATE SEQUENCE public.selected_plans_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.selected_plans_id_seq;
       public          postgres    false    217                       0    0    selected_plans_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.selected_plans_id_seq OWNED BY public.accommodation_modality.id;
          public          postgres    false    250            �            1259    116315    service_contract    TABLE     u   CREATE TABLE public.service_contract (
    id_contract integer NOT NULL,
    service_provider_id integer NOT NULL
);
 $   DROP TABLE public.service_contract;
       public         heap    postgres    false            �            1259    116318    service_modality    TABLE     �   CREATE TABLE public.service_modality (
    modality_id integer NOT NULL,
    service_contract_id integer NOT NULL,
    activity_id integer NOT NULL,
    price double precision NOT NULL,
    release_date date NOT NULL
);
 $   DROP TABLE public.service_modality;
       public         heap    postgres    false            �            1259    116321    service_provider    TABLE     B   CREATE TABLE public.service_provider (
    id integer NOT NULL
);
 $   DROP TABLE public.service_provider;
       public         heap    postgres    false            �            1259    116324    service_provider_id_seq    SEQUENCE     �   CREATE SEQUENCE public.service_provider_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.service_provider_id_seq;
       public          postgres    false    253                       0    0    service_provider_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.service_provider_id_seq OWNED BY public.service_provider.id;
          public          postgres    false    254            �            1259    116325    tourist_package    TABLE     �   CREATE TABLE public.tourist_package (
    id_tourist_package integer NOT NULL,
    name character varying NOT NULL,
    cant_max_pax integer NOT NULL,
    cant_reserves integer NOT NULL,
    start_date date NOT NULL,
    termination_date date NOT NULL
);
 #   DROP TABLE public.tourist_package;
       public         heap    postgres    false                        1259    116330 &   tourist_package_id_tourist_package_seq    SEQUENCE     �   CREATE SEQUENCE public.tourist_package_id_tourist_package_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.tourist_package_id_tourist_package_seq;
       public          postgres    false    255                       0    0 &   tourist_package_id_tourist_package_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.tourist_package_id_tourist_package_seq OWNED BY public.tourist_package.id_tourist_package;
          public          postgres    false    256                       1259    116331    tourist_package_modality    TABLE     |   CREATE TABLE public.tourist_package_modality (
    modality_id integer NOT NULL,
    tourist_package_id integer NOT NULL
);
 ,   DROP TABLE public.tourist_package_modality;
       public         heap    postgres    false                       1259    116334    transport_modality    TABLE     �   CREATE TABLE public.transport_modality (
    modality_id integer NOT NULL,
    carrier_contract_id integer NOT NULL,
    type_transport_modality character varying(45) NOT NULL
);
 &   DROP TABLE public.transport_modality;
       public         heap    postgres    false                       1259    116337    transport_modality_vehicle    TABLE     �   CREATE TABLE public.transport_modality_vehicle (
    transport_modality_id integer NOT NULL,
    vehicle_id integer NOT NULL
);
 .   DROP TABLE public.transport_modality_vehicle;
       public         heap    postgres    false                       1259    116340    transportation_provider    TABLE     I   CREATE TABLE public.transportation_provider (
    id integer NOT NULL
);
 +   DROP TABLE public.transportation_provider;
       public         heap    postgres    false                       1259    116343    transportation_provider_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transportation_provider_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.transportation_provider_id_seq;
       public          postgres    false    260                       0    0    transportation_provider_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.transportation_provider_id_seq OWNED BY public.transportation_provider.id;
          public          postgres    false    261                       1259    116344    type_of_room    TABLE     u   CREATE TABLE public.type_of_room (
    id integer NOT NULL,
    type_of_room_name character varying(100) NOT NULL
);
     DROP TABLE public.type_of_room;
       public         heap    postgres    false                       1259    116347    type_of_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.type_of_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.type_of_room_id_seq;
       public          postgres    false    262                       0    0    type_of_room_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.type_of_room_id_seq OWNED BY public.type_of_room.id;
          public          postgres    false    263                       1259    116348    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false            	           1259    116349    user    TABLE     O  CREATE TABLE public."user" (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    user_name character varying NOT NULL,
    user_password character varying NOT NULL,
    id_rol integer NOT NULL,
    start_date_connection date,
    last_date_connection date,
    connected boolean,
    state_password boolean
);
    DROP TABLE public."user";
       public         heap    postgres    false    264            
           1259    116355    vehicle    TABLE     g  CREATE TABLE public.vehicle (
    id_vehicle integer NOT NULL,
    lock character varying(45) NOT NULL,
    transportation_provider_id integer NOT NULL,
    brand character varying NOT NULL,
    capacity_without_luggage integer NOT NULL,
    capacity_with_luggage integer NOT NULL,
    date_of_production date NOT NULL,
    total_capacity integer NOT NULL
);
    DROP TABLE public.vehicle;
       public         heap    postgres    false                       1259    116360    vehicle_id_vehicle_seq    SEQUENCE     �   CREATE SEQUENCE public.vehicle_id_vehicle_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vehicle_id_vehicle_seq;
       public          postgres    false    266                       0    0    vehicle_id_vehicle_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vehicle_id_vehicle_seq OWNED BY public.vehicle.id_vehicle;
          public          postgres    false    267            �           2604    116361    activity id_activity    DEFAULT     |   ALTER TABLE ONLY public.activity ALTER COLUMN id_activity SET DEFAULT nextval('public.activity_id_activity_seq'::regclass);
 C   ALTER TABLE public.activity ALTER COLUMN id_activity DROP DEFAULT;
       public          postgres    false    219    218            �           2604    116362    contract id_contract    DEFAULT     |   ALTER TABLE ONLY public.contract ALTER COLUMN id_contract SET DEFAULT nextval('public.contract_id_contract_seq'::regclass);
 C   ALTER TABLE public.contract ALTER COLUMN id_contract DROP DEFAULT;
       public          postgres    false    223    222            �           2604    116363    meal_plan id    DEFAULT     l   ALTER TABLE ONLY public.meal_plan ALTER COLUMN id SET DEFAULT nextval('public.meal_plan_id_seq'::regclass);
 ;   ALTER TABLE public.meal_plan ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    236            �           2604    116364    modality id    DEFAULT     j   ALTER TABLE ONLY public.modality ALTER COLUMN id SET DEFAULT nextval('public.modality_id_seq'::regclass);
 :   ALTER TABLE public.modality ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    238            �           2604    116365 	   season id    DEFAULT     f   ALTER TABLE ONLY public.season ALTER COLUMN id SET DEFAULT nextval('public.season_id_seq'::regclass);
 8   ALTER TABLE public.season ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    249    248            �           2604    116366 "   tourist_package id_tourist_package    DEFAULT     �   ALTER TABLE ONLY public.tourist_package ALTER COLUMN id_tourist_package SET DEFAULT nextval('public.tourist_package_id_tourist_package_seq'::regclass);
 Q   ALTER TABLE public.tourist_package ALTER COLUMN id_tourist_package DROP DEFAULT;
       public          postgres    false    256    255            �           2604    116367    type_of_room id    DEFAULT     r   ALTER TABLE ONLY public.type_of_room ALTER COLUMN id SET DEFAULT nextval('public.type_of_room_id_seq'::regclass);
 >   ALTER TABLE public.type_of_room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    263    262            �           2604    116368    vehicle id_vehicle    DEFAULT     x   ALTER TABLE ONLY public.vehicle ALTER COLUMN id_vehicle SET DEFAULT nextval('public.vehicle_id_vehicle_seq'::regclass);
 A   ALTER TABLE public.vehicle ALTER COLUMN id_vehicle DROP DEFAULT;
       public          postgres    false    267    266            �          0    116216    accommodation_contract 
   TABLE DATA           G   COPY public.accommodation_contract (id_contract, hotel_id) FROM stdin;
    public          postgres    false    216   ��      �          0    116219    accommodation_modality 
   TABLE DATA           �   COPY public.accommodation_modality (id, accommodation_contract_id, type_of_room_id, meal_plan_id, price, cant_days_accommodation, hotel_modality_id) FROM stdin;
    public          postgres    false    217   �      �          0    116222    activity 
   TABLE DATA           `   COPY public.activity (id_activity, activity_description, service_provider_id, name) FROM stdin;
    public          postgres    false    218   W�      �          0    116228    administrator 
   TABLE DATA           0   COPY public.administrator (user_id) FROM stdin;
    public          postgres    false    220   0�      �          0    116231    carrier_contract 
   TABLE DATA           S   COPY public.carrier_contract (id_contract, transportation_provider_id) FROM stdin;
    public          postgres    false    221   P�      �          0    116234    contract 
   TABLE DATA           �   COPY public.contract (id_contract, contract_start_date, contract_termination_date, contract_description, type_of_contract, state, contract_reconcilation_date, surcharge) FROM stdin;
    public          postgres    false    222   y�      �          0    116238    cost_kilometers 
   TABLE DATA           s   COPY public.cost_kilometers (modality_id, cost_kilometers_going, cost_kilometers_lap, cost_hours_wait) FROM stdin;
    public          postgres    false    224   _�      �          0    116241 	   dependent 
   TABLE DATA           ,   COPY public.dependent (user_id) FROM stdin;
    public          postgres    false    225   ��      �          0    116244    established_route 
   TABLE DATA           `   COPY public.established_route (modality_id, description_rout, cost_going, cost_lap) FROM stdin;
    public          postgres    false    226   ��      �          0    116247    hotel 
   TABLE DATA           �   COPY public.hotel (id, hotel_chain, address, hotel_category, phone, fax, email, cant_rooms, cant_floors, location_hotel, distance_nearest_city, distance_airport, date_built) FROM stdin;
    public          postgres    false    227   ��      �          0    116252    hotel_hotel_modality 
   TABLE DATA           K   COPY public.hotel_hotel_modality (hotel_id, hotel_modality_id) FROM stdin;
    public          postgres    false    228   h�      �          0    116256    hotel_meal_plan 
   TABLE DATA           A   COPY public.hotel_meal_plan (hotel_id, meal_plan_id) FROM stdin;
    public          postgres    false    230          �          0    116260    hotel_modality 
   TABLE DATA           2   COPY public.hotel_modality (id, name) FROM stdin;
    public          postgres    false    232   �       �          0    116266    hotel_type_of_room 
   TABLE DATA           G   COPY public.hotel_type_of_room (hotel_id, type_of_room_id) FROM stdin;
    public          postgres    false    233         �          0    116269    hours_kilometers 
   TABLE DATA           �   COPY public.hours_kilometers (modality_id, cost_kilometers_rout, cost_hours, cost_kilometers_rout_additionals, cost_hours_additionals) FROM stdin;
    public          postgres    false    234   �      �          0    116272    manager 
   TABLE DATA           *   COPY public.manager (user_id) FROM stdin;
    public          postgres    false    235   �      �          0    116275 	   meal_plan 
   TABLE DATA           7   COPY public.meal_plan (id, meal_plan_name) FROM stdin;
    public          postgres    false    236   �      �          0    116279    modality 
   TABLE DATA           8   COPY public.modality (id, type_of_modality) FROM stdin;
    public          postgres    false    238   F      �          0    116283    package_designer 
   TABLE DATA           3   COPY public.package_designer (user_id) FROM stdin;
    public          postgres    false    240   �      �          0    116286    password_change_request 
   TABLE DATA           5   COPY public.password_change_request (id) FROM stdin;
    public          postgres    false    241   �      �          0    116290    provider 
   TABLE DATA           ?   COPY public.provider (id_provider, name, province) FROM stdin;
    public          postgres    false    243         �          0    116297    request 
   TABLE DATA           J   COPY public.request (id, type_request, date_request, user_id) FROM stdin;
    public          postgres    false    245   g      �          0    116304    rol 
   TABLE DATA           +   COPY public.rol (id, rol_name) FROM stdin;
    public          postgres    false    247   �      �          0    116310    season 
   TABLE DATA           �   COPY public.season (id, season_name, season_start_date, season_termination_date, season_description, type_of_season, accommodation_contract_id) FROM stdin;
    public          postgres    false    248   �      �          0    116315    service_contract 
   TABLE DATA           L   COPY public.service_contract (id_contract, service_provider_id) FROM stdin;
    public          postgres    false    251   �      �          0    116318    service_modality 
   TABLE DATA           n   COPY public.service_modality (modality_id, service_contract_id, activity_id, price, release_date) FROM stdin;
    public          postgres    false    252   �      �          0    116321    service_provider 
   TABLE DATA           .   COPY public.service_provider (id) FROM stdin;
    public          postgres    false    253   P      �          0    116325    tourist_package 
   TABLE DATA           ~   COPY public.tourist_package (id_tourist_package, name, cant_max_pax, cant_reserves, start_date, termination_date) FROM stdin;
    public          postgres    false    255   �      �          0    116331    tourist_package_modality 
   TABLE DATA           S   COPY public.tourist_package_modality (modality_id, tourist_package_id) FROM stdin;
    public          postgres    false    257   �      �          0    116334    transport_modality 
   TABLE DATA           g   COPY public.transport_modality (modality_id, carrier_contract_id, type_transport_modality) FROM stdin;
    public          postgres    false    258   F	      �          0    116337    transport_modality_vehicle 
   TABLE DATA           W   COPY public.transport_modality_vehicle (transport_modality_id, vehicle_id) FROM stdin;
    public          postgres    false    259   �	      �          0    116340    transportation_provider 
   TABLE DATA           5   COPY public.transportation_provider (id) FROM stdin;
    public          postgres    false    260   �	      �          0    116344    type_of_room 
   TABLE DATA           =   COPY public.type_of_room (id, type_of_room_name) FROM stdin;
    public          postgres    false    262   !
      �          0    116349    user 
   TABLE DATA           �   COPY public."user" (id, user_name, user_password, id_rol, start_date_connection, last_date_connection, connected, state_password) FROM stdin;
    public          postgres    false    265   b
      �          0    116355    vehicle 
   TABLE DATA           �   COPY public.vehicle (id_vehicle, lock, transportation_provider_id, brand, capacity_without_luggage, capacity_with_luggage, date_of_production, total_capacity) FROM stdin;
    public          postgres    false    266   Q                 0    0    activity_id_activity_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.activity_id_activity_seq', 76, true);
          public          postgres    false    219                       0    0    contract_id_contract_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.contract_id_contract_seq', 56, true);
          public          postgres    false    223                       0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 1, false);
          public          postgres    false    229                       0    0    hotel_modality_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hotel_modality_id_seq', 6, true);
          public          postgres    false    231                       0    0    meal_plan_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.meal_plan_id_seq', 4, true);
          public          postgres    false    237                       0    0    modality_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.modality_id_seq', 113, true);
          public          postgres    false    239                       0    0    provider_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.provider_id_seq', 138, true);
          public          postgres    false    242                       0    0    request_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.request_id_seq', 1, false);
          public          postgres    false    244                       0    0 
   rol_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.rol_id_seq', 4, true);
          public          postgres    false    246                       0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 26, true);
          public          postgres    false    249                       0    0    selected_plans_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.selected_plans_id_seq', 1, false);
          public          postgres    false    250                       0    0    service_provider_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.service_provider_id_seq', 1, false);
          public          postgres    false    254                        0    0 &   tourist_package_id_tourist_package_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.tourist_package_id_tourist_package_seq', 18, true);
          public          postgres    false    256            !           0    0    transportation_provider_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.transportation_provider_id_seq', 3, true);
          public          postgres    false    261            "           0    0    type_of_room_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.type_of_room_id_seq', 4, true);
          public          postgres    false    263            #           0    0    user_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.user_id_seq', 180, true);
          public          postgres    false    264            $           0    0    vehicle_id_vehicle_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.vehicle_id_vehicle_seq', 84, true);
          public          postgres    false    267            �           2606    116370     administrator administrator_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT administrator_pkey PRIMARY KEY (user_id);
 J   ALTER TABLE ONLY public.administrator DROP CONSTRAINT administrator_pkey;
       public            postgres    false    220            �           2606    116372    dependent dependent_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.dependent
    ADD CONSTRAINT dependent_pkey PRIMARY KEY (user_id);
 B   ALTER TABLE ONLY public.dependent DROP CONSTRAINT dependent_pkey;
       public            postgres    false    225            �           2606    116374 .   hotel_hotel_modality hotel_hotel_modality_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.hotel_hotel_modality
    ADD CONSTRAINT hotel_hotel_modality_pkey PRIMARY KEY (hotel_id, hotel_modality_id);
 X   ALTER TABLE ONLY public.hotel_hotel_modality DROP CONSTRAINT hotel_hotel_modality_pkey;
       public            postgres    false    228    228            �           2606    116376 "   hotel_modality hotel_modality_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.hotel_modality
    ADD CONSTRAINT hotel_modality_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.hotel_modality DROP CONSTRAINT hotel_modality_pkey;
       public            postgres    false    232            �           2606    116378    manager manager_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_pkey PRIMARY KEY (user_id);
 >   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_pkey;
       public            postgres    false    235            �           2606    116380 &   package_designer package_designer_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.package_designer
    ADD CONSTRAINT package_designer_pkey PRIMARY KEY (user_id);
 P   ALTER TABLE ONLY public.package_designer DROP CONSTRAINT package_designer_pkey;
       public            postgres    false    240            �           2606    116382 4   password_change_request password_change_request_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.password_change_request
    ADD CONSTRAINT password_change_request_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.password_change_request DROP CONSTRAINT password_change_request_pkey;
       public            postgres    false    241            �           2606    116384 0   accommodation_contract pk_accommodation_contract 
   CONSTRAINT     w   ALTER TABLE ONLY public.accommodation_contract
    ADD CONSTRAINT pk_accommodation_contract PRIMARY KEY (id_contract);
 Z   ALTER TABLE ONLY public.accommodation_contract DROP CONSTRAINT pk_accommodation_contract;
       public            postgres    false    216            �           2606    116386    activity pk_activity 
   CONSTRAINT     [   ALTER TABLE ONLY public.activity
    ADD CONSTRAINT pk_activity PRIMARY KEY (id_activity);
 >   ALTER TABLE ONLY public.activity DROP CONSTRAINT pk_activity;
       public            postgres    false    218            �           2606    116388 $   carrier_contract pk_carrier_contract 
   CONSTRAINT     k   ALTER TABLE ONLY public.carrier_contract
    ADD CONSTRAINT pk_carrier_contract PRIMARY KEY (id_contract);
 N   ALTER TABLE ONLY public.carrier_contract DROP CONSTRAINT pk_carrier_contract;
       public            postgres    false    221            �           2606    116390    contract pk_contract 
   CONSTRAINT     [   ALTER TABLE ONLY public.contract
    ADD CONSTRAINT pk_contract PRIMARY KEY (id_contract);
 >   ALTER TABLE ONLY public.contract DROP CONSTRAINT pk_contract;
       public            postgres    false    222            �           2606    116392 "   cost_kilometers pk_cost_kilometers 
   CONSTRAINT     i   ALTER TABLE ONLY public.cost_kilometers
    ADD CONSTRAINT pk_cost_kilometers PRIMARY KEY (modality_id);
 L   ALTER TABLE ONLY public.cost_kilometers DROP CONSTRAINT pk_cost_kilometers;
       public            postgres    false    224            �           2606    116394 &   established_route pk_established_route 
   CONSTRAINT     m   ALTER TABLE ONLY public.established_route
    ADD CONSTRAINT pk_established_route PRIMARY KEY (modality_id);
 P   ALTER TABLE ONLY public.established_route DROP CONSTRAINT pk_established_route;
       public            postgres    false    226            �           2606    116396    hotel pk_hotel 
   CONSTRAINT     L   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT pk_hotel PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.hotel DROP CONSTRAINT pk_hotel;
       public            postgres    false    227            �           2606    116398 "   hotel_meal_plan pk_hotel_meal_plan 
   CONSTRAINT     t   ALTER TABLE ONLY public.hotel_meal_plan
    ADD CONSTRAINT pk_hotel_meal_plan PRIMARY KEY (hotel_id, meal_plan_id);
 L   ALTER TABLE ONLY public.hotel_meal_plan DROP CONSTRAINT pk_hotel_meal_plan;
       public            postgres    false    230    230            �           2606    116400 (   hotel_type_of_room pk_hotel_type_of_room 
   CONSTRAINT     }   ALTER TABLE ONLY public.hotel_type_of_room
    ADD CONSTRAINT pk_hotel_type_of_room PRIMARY KEY (hotel_id, type_of_room_id);
 R   ALTER TABLE ONLY public.hotel_type_of_room DROP CONSTRAINT pk_hotel_type_of_room;
       public            postgres    false    233    233            �           2606    116402 $   hours_kilometers pk_hours_kilometers 
   CONSTRAINT     k   ALTER TABLE ONLY public.hours_kilometers
    ADD CONSTRAINT pk_hours_kilometers PRIMARY KEY (modality_id);
 N   ALTER TABLE ONLY public.hours_kilometers DROP CONSTRAINT pk_hours_kilometers;
       public            postgres    false    234            �           2606    116404    meal_plan pk_meal_plan 
   CONSTRAINT     T   ALTER TABLE ONLY public.meal_plan
    ADD CONSTRAINT pk_meal_plan PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.meal_plan DROP CONSTRAINT pk_meal_plan;
       public            postgres    false    236            �           2606    116406    modality pk_modality 
   CONSTRAINT     R   ALTER TABLE ONLY public.modality
    ADD CONSTRAINT pk_modality PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.modality DROP CONSTRAINT pk_modality;
       public            postgres    false    238            �           2606    116408    season pk_season 
   CONSTRAINT     N   ALTER TABLE ONLY public.season
    ADD CONSTRAINT pk_season PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.season DROP CONSTRAINT pk_season;
       public            postgres    false    248            �           2606    116410 (   accommodation_modality pk_selected_plans 
   CONSTRAINT     f   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT pk_selected_plans PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT pk_selected_plans;
       public            postgres    false    217            �           2606    116412 $   service_contract pk_service_contract 
   CONSTRAINT     k   ALTER TABLE ONLY public.service_contract
    ADD CONSTRAINT pk_service_contract PRIMARY KEY (id_contract);
 N   ALTER TABLE ONLY public.service_contract DROP CONSTRAINT pk_service_contract;
       public            postgres    false    251            �           2606    116414 $   service_modality pk_service_modality 
   CONSTRAINT     k   ALTER TABLE ONLY public.service_modality
    ADD CONSTRAINT pk_service_modality PRIMARY KEY (modality_id);
 N   ALTER TABLE ONLY public.service_modality DROP CONSTRAINT pk_service_modality;
       public            postgres    false    252            �           2606    116416 $   service_provider pk_service_provider 
   CONSTRAINT     b   ALTER TABLE ONLY public.service_provider
    ADD CONSTRAINT pk_service_provider PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.service_provider DROP CONSTRAINT pk_service_provider;
       public            postgres    false    253            �           2606    116418 "   tourist_package pk_tourist_package 
   CONSTRAINT     p   ALTER TABLE ONLY public.tourist_package
    ADD CONSTRAINT pk_tourist_package PRIMARY KEY (id_tourist_package);
 L   ALTER TABLE ONLY public.tourist_package DROP CONSTRAINT pk_tourist_package;
       public            postgres    false    255            �           2606    116420 4   tourist_package_modality pk_tourist_package_modality 
   CONSTRAINT     �   ALTER TABLE ONLY public.tourist_package_modality
    ADD CONSTRAINT pk_tourist_package_modality PRIMARY KEY (modality_id, tourist_package_id);
 ^   ALTER TABLE ONLY public.tourist_package_modality DROP CONSTRAINT pk_tourist_package_modality;
       public            postgres    false    257    257                       2606    116422 (   transport_modality pk_transport_modality 
   CONSTRAINT     o   ALTER TABLE ONLY public.transport_modality
    ADD CONSTRAINT pk_transport_modality PRIMARY KEY (modality_id);
 R   ALTER TABLE ONLY public.transport_modality DROP CONSTRAINT pk_transport_modality;
       public            postgres    false    258                       2606    116424 2   transportation_provider pk_transportation_provider 
   CONSTRAINT     p   ALTER TABLE ONLY public.transportation_provider
    ADD CONSTRAINT pk_transportation_provider PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.transportation_provider DROP CONSTRAINT pk_transportation_provider;
       public            postgres    false    260                       2606    116426    type_of_room pk_type_of_room 
   CONSTRAINT     Z   ALTER TABLE ONLY public.type_of_room
    ADD CONSTRAINT pk_type_of_room PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.type_of_room DROP CONSTRAINT pk_type_of_room;
       public            postgres    false    262                       2606    116428    vehicle pk_vehicle 
   CONSTRAINT     X   ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT pk_vehicle PRIMARY KEY (id_vehicle);
 <   ALTER TABLE ONLY public.vehicle DROP CONSTRAINT pk_vehicle;
       public            postgres    false    266            �           2606    116430    provider provider_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.provider
    ADD CONSTRAINT provider_pkey PRIMARY KEY (id_provider);
 @   ALTER TABLE ONLY public.provider DROP CONSTRAINT provider_pkey;
       public            postgres    false    243            �           2606    116432    request request_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.request DROP CONSTRAINT request_pkey;
       public            postgres    false    245            �           2606    116434    rol rol_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public            postgres    false    247                       2606    116436 :   transport_modality_vehicle transport_modality_vehicle_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.transport_modality_vehicle
    ADD CONSTRAINT transport_modality_vehicle_pkey PRIMARY KEY (transport_modality_id, vehicle_id);
 d   ALTER TABLE ONLY public.transport_modality_vehicle DROP CONSTRAINT transport_modality_vehicle_pkey;
       public            postgres    false    259    259            	           2606    116438    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    265            �           1259    116439 "   fk_contrato_alojamiento_hotel1_idx    INDEX     i   CREATE INDEX fk_contrato_alojamiento_hotel1_idx ON public.accommodation_contract USING btree (hotel_id);
 6   DROP INDEX public.fk_contrato_alojamiento_hotel1_idx;
       public            postgres    false    216            �           1259    116440 1   fk_contrato_servicio_has_actividad_actividad1_idx    INDEX     u   CREATE INDEX fk_contrato_servicio_has_actividad_actividad1_idx ON public.service_modality USING btree (activity_id);
 E   DROP INDEX public.fk_contrato_servicio_has_actividad_actividad1_idx;
       public            postgres    false    252            �           1259    116441 9   fk_contrato_servicio_has_actividad_contrato_servicio1_idx    INDEX     �   CREATE INDEX fk_contrato_servicio_has_actividad_contrato_servicio1_idx ON public.service_modality USING btree (service_contract_id);
 M   DROP INDEX public.fk_contrato_servicio_has_actividad_contrato_servicio1_idx;
       public            postgres    false    252            �           1259    116442 1   fk_contrato_servicio_prestatario_de_servicio1_idx    INDEX     }   CREATE INDEX fk_contrato_servicio_prestatario_de_servicio1_idx ON public.service_contract USING btree (service_provider_id);
 E   DROP INDEX public.fk_contrato_servicio_prestatario_de_servicio1_idx;
       public            postgres    false    251            �           1259    116443 5   fk_contrato_transportista_prestatario_transporte1_idx    INDEX     �   CREATE INDEX fk_contrato_transportista_prestatario_transporte1_idx ON public.carrier_contract USING btree (transportation_provider_id);
 I   DROP INDEX public.fk_contrato_transportista_prestatario_transporte1_idx;
       public            postgres    false    221            �           1259    116444 !   fk_hotel_has_meal_plan_hotel1_idx    INDEX     a   CREATE INDEX fk_hotel_has_meal_plan_hotel1_idx ON public.hotel_meal_plan USING btree (hotel_id);
 5   DROP INDEX public.fk_hotel_has_meal_plan_hotel1_idx;
       public            postgres    false    230            �           1259    116445 %   fk_hotel_has_meal_plan_meal_plan1_idx    INDEX     i   CREATE INDEX fk_hotel_has_meal_plan_meal_plan1_idx ON public.hotel_meal_plan USING btree (meal_plan_id);
 9   DROP INDEX public.fk_hotel_has_meal_plan_meal_plan1_idx;
       public            postgres    false    230            �           1259    116446 $   fk_hotel_has_type_of_room_hotel1_idx    INDEX     g   CREATE INDEX fk_hotel_has_type_of_room_hotel1_idx ON public.hotel_type_of_room USING btree (hotel_id);
 8   DROP INDEX public.fk_hotel_has_type_of_room_hotel1_idx;
       public            postgres    false    233            �           1259    116447 +   fk_hotel_has_type_of_room_type_of_room1_idx    INDEX     u   CREATE INDEX fk_hotel_has_type_of_room_type_of_room1_idx ON public.hotel_type_of_room USING btree (type_of_room_id);
 ?   DROP INDEX public.fk_hotel_has_type_of_room_type_of_room1_idx;
       public            postgres    false    233            �           1259    116448 3   fk_modalidad_contratada_contrato_transportista1_idx    INDEX     �   CREATE INDEX fk_modalidad_contratada_contrato_transportista1_idx ON public.transport_modality USING btree (carrier_contract_id);
 G   DROP INDEX public.fk_modalidad_contratada_contrato_transportista1_idx;
       public            postgres    false    258            �           1259    116449 -   fk_modality_has_tourist_package_modality1_idx    INDEX     y   CREATE INDEX fk_modality_has_tourist_package_modality1_idx ON public.tourist_package_modality USING btree (modality_id);
 A   DROP INDEX public.fk_modality_has_tourist_package_modality1_idx;
       public            postgres    false    257            �           1259    116450 4   fk_modality_has_tourist_package_tourist_package1_idx    INDEX     �   CREATE INDEX fk_modality_has_tourist_package_tourist_package1_idx ON public.tourist_package_modality USING btree (tourist_package_id);
 H   DROP INDEX public.fk_modality_has_tourist_package_tourist_package1_idx;
       public            postgres    false    257            �           1259    116451 -   fk_planes_escogidos_contrato_alojamiento1_idx    INDEX     �   CREATE INDEX fk_planes_escogidos_contrato_alojamiento1_idx ON public.accommodation_modality USING btree (accommodation_contract_id);
 A   DROP INDEX public.fk_planes_escogidos_contrato_alojamiento1_idx;
       public            postgres    false    217            �           1259    116452     fk_selected_plans_meal_plan1_idx    INDEX     k   CREATE INDEX fk_selected_plans_meal_plan1_idx ON public.accommodation_modality USING btree (meal_plan_id);
 4   DROP INDEX public.fk_selected_plans_meal_plan1_idx;
       public            postgres    false    217            �           1259    116453 #   fk_selected_plans_type_of_room1_idx    INDEX     q   CREATE INDEX fk_selected_plans_type_of_room1_idx ON public.accommodation_modality USING btree (type_of_room_id);
 7   DROP INDEX public.fk_selected_plans_type_of_room1_idx;
       public            postgres    false    217            
           1259    116454 '   fk_vehiculo_prestatario_transporte1_idx    INDEX     q   CREATE INDEX fk_vehiculo_prestatario_transporte1_idx ON public.vehicle USING btree (transportation_provider_id);
 ;   DROP INDEX public.fk_vehiculo_prestatario_transporte1_idx;
       public            postgres    false    266            7           2620    116455 U   accommodation_modality trigger_delete_reference_modality_table_accommodation_modality    TRIGGER     �   CREATE TRIGGER trigger_delete_reference_modality_table_accommodation_modality AFTER DELETE ON public.accommodation_modality FOR EACH ROW EXECUTE FUNCTION public.delete_reference_modality_table_accommodation_modality();
 n   DROP TRIGGER trigger_delete_reference_modality_table_accommodation_modality ON public.accommodation_modality;
       public          postgres    false    456    217            :           2620    116456 I   service_modality trigger_delete_reference_modality_table_service_modality    TRIGGER     �   CREATE TRIGGER trigger_delete_reference_modality_table_service_modality AFTER DELETE ON public.service_modality FOR EACH ROW EXECUTE FUNCTION public.delete_reference_modality_table_service_modality();
 b   DROP TRIGGER trigger_delete_reference_modality_table_service_modality ON public.service_modality;
       public          postgres    false    252    297            ;           2620    116457 M   transport_modality trigger_delete_reference_modality_table_transport_modality    TRIGGER     �   CREATE TRIGGER trigger_delete_reference_modality_table_transport_modality AFTER DELETE ON public.transport_modality FOR EACH ROW EXECUTE FUNCTION public.delete_reference_modality_table_transport_modality();
 f   DROP TRIGGER trigger_delete_reference_modality_table_transport_modality ON public.transport_modality;
       public          postgres    false    334    258            8           2620    116458 D   accommodation_modality trigger_no_repitencias_accommodation_modality    TRIGGER     �   CREATE TRIGGER trigger_no_repitencias_accommodation_modality BEFORE INSERT ON public.accommodation_modality FOR EACH ROW EXECUTE FUNCTION public.validar_repitencia_accommodation_modality();
 ]   DROP TRIGGER trigger_no_repitencias_accommodation_modality ON public.accommodation_modality;
       public          postgres    false    284    217            =           2620    116459 +   vehicle trigger_no_repitencias_lock_vehicle    TRIGGER     �   CREATE TRIGGER trigger_no_repitencias_lock_vehicle BEFORE INSERT OR UPDATE ON public.vehicle FOR EACH ROW EXECUTE FUNCTION public.validar_repitencia_lock_vehicle();
 D   DROP TRIGGER trigger_no_repitencias_lock_vehicle ON public.vehicle;
       public          postgres    false    268    266            9           2620    116460 -   provider trigger_no_repitencias_provider_name    TRIGGER     �   CREATE TRIGGER trigger_no_repitencias_provider_name BEFORE INSERT OR UPDATE ON public.provider FOR EACH ROW EXECUTE FUNCTION public.validar_repitencias_provider_name();
 F   DROP TRIGGER trigger_no_repitencias_provider_name ON public.provider;
       public          postgres    false    464    243            <           2620    116461 %   user trigger_no_repitencias_user_name    TRIGGER     �   CREATE TRIGGER trigger_no_repitencias_user_name BEFORE INSERT OR UPDATE OF user_name ON public."user" FOR EACH ROW EXECUTE FUNCTION public.validar_repitencias_user_name();
 @   DROP TRIGGER trigger_no_repitencias_user_name ON public."user";
       public          postgres    false    265    265    395                       2606    116462 <   accommodation_modality fk_accommodation_modality_id_modality    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT fk_accommodation_modality_id_modality FOREIGN KEY (id) REFERENCES public.modality(id) ON DELETE CASCADE;
 f   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT fk_accommodation_modality_id_modality;
       public          postgres    false    238    217    5091                       2606    116467 (   activity fk_activity_service_provider_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity
    ADD CONSTRAINT fk_activity_service_provider_id FOREIGN KEY (service_provider_id) REFERENCES public.service_provider(id) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.activity DROP CONSTRAINT fk_activity_service_provider_id;
       public          postgres    false    218    5112    253                       2606    116472 8   accommodation_contract fk_contrato_alojamiento_contrato1    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_contract
    ADD CONSTRAINT fk_contrato_alojamiento_contrato1 FOREIGN KEY (id_contract) REFERENCES public.contract(id_contract) ON DELETE CASCADE;
 b   ALTER TABLE ONLY public.accommodation_contract DROP CONSTRAINT fk_contrato_alojamiento_contrato1;
       public          postgres    false    222    5063    216                       2606    116477 5   accommodation_contract fk_contrato_alojamiento_hotel1    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_contract
    ADD CONSTRAINT fk_contrato_alojamiento_hotel1 FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.accommodation_contract DROP CONSTRAINT fk_contrato_alojamiento_hotel1;
       public          postgres    false    5071    227    216            (           2606    116482 /   service_contract fk_contrato_servicio_contrato1    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_contract
    ADD CONSTRAINT fk_contrato_servicio_contrato1 FOREIGN KEY (id_contract) REFERENCES public.contract(id_contract) ON DELETE CASCADE;
 Y   ALTER TABLE ONLY public.service_contract DROP CONSTRAINT fk_contrato_servicio_contrato1;
       public          postgres    false    5063    251    222            *           2606    116487 >   service_modality fk_contrato_servicio_has_actividad_actividad1    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_modality
    ADD CONSTRAINT fk_contrato_servicio_has_actividad_actividad1 FOREIGN KEY (activity_id) REFERENCES public.activity(id_activity) ON DELETE CASCADE;
 h   ALTER TABLE ONLY public.service_modality DROP CONSTRAINT fk_contrato_servicio_has_actividad_actividad1;
       public          postgres    false    5056    218    252            +           2606    116492 F   service_modality fk_contrato_servicio_has_actividad_contrato_servicio1    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_modality
    ADD CONSTRAINT fk_contrato_servicio_has_actividad_contrato_servicio1 FOREIGN KEY (service_contract_id) REFERENCES public.service_contract(id_contract) ON DELETE CASCADE;
 p   ALTER TABLE ONLY public.service_modality DROP CONSTRAINT fk_contrato_servicio_has_actividad_contrato_servicio1;
       public          postgres    false    5106    252    251            )           2606    116497 >   service_contract fk_contrato_servicio_prestatario_de_servicio1    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_contract
    ADD CONSTRAINT fk_contrato_servicio_prestatario_de_servicio1 FOREIGN KEY (service_provider_id) REFERENCES public.service_provider(id) ON DELETE CASCADE;
 h   ALTER TABLE ONLY public.service_contract DROP CONSTRAINT fk_contrato_servicio_prestatario_de_servicio1;
       public          postgres    false    251    5112    253                       2606    116502 4   carrier_contract fk_contrato_transportista_contrato1    FK CONSTRAINT     �   ALTER TABLE ONLY public.carrier_contract
    ADD CONSTRAINT fk_contrato_transportista_contrato1 FOREIGN KEY (id_contract) REFERENCES public.contract(id_contract) ON DELETE CASCADE;
 ^   ALTER TABLE ONLY public.carrier_contract DROP CONSTRAINT fk_contrato_transportista_contrato1;
       public          postgres    false    5063    222    221                       2606    116507 B   carrier_contract fk_contrato_transportista_prestatario_transporte1    FK CONSTRAINT     �   ALTER TABLE ONLY public.carrier_contract
    ADD CONSTRAINT fk_contrato_transportista_prestatario_transporte1 FOREIGN KEY (transportation_provider_id) REFERENCES public.transportation_provider(id) ON DELETE CASCADE;
 l   ALTER TABLE ONLY public.carrier_contract DROP CONSTRAINT fk_contrato_transportista_prestatario_transporte1;
       public          postgres    false    221    260    5125                       2606    116512 6   cost_kilometers fk_cost_kilometers_transport_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.cost_kilometers
    ADD CONSTRAINT fk_cost_kilometers_transport_modality1 FOREIGN KEY (modality_id) REFERENCES public.transport_modality(modality_id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.cost_kilometers DROP CONSTRAINT fk_cost_kilometers_transport_modality1;
       public          postgres    false    258    5121    224                       2606    116517 :   established_route fk_established_route_transport_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.established_route
    ADD CONSTRAINT fk_established_route_transport_modality1 FOREIGN KEY (modality_id) REFERENCES public.transport_modality(modality_id) ON DELETE CASCADE;
 d   ALTER TABLE ONLY public.established_route DROP CONSTRAINT fk_established_route_transport_modality1;
       public          postgres    false    258    5121    226                       2606    116522 -   hotel_meal_plan fk_hotel_has_meal_plan_hotel1    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_meal_plan
    ADD CONSTRAINT fk_hotel_has_meal_plan_hotel1 FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.hotel_meal_plan DROP CONSTRAINT fk_hotel_has_meal_plan_hotel1;
       public          postgres    false    230    227    5071                       2606    116527 1   hotel_meal_plan fk_hotel_has_meal_plan_meal_plan1    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_meal_plan
    ADD CONSTRAINT fk_hotel_has_meal_plan_meal_plan1 FOREIGN KEY (meal_plan_id) REFERENCES public.meal_plan(id) ON DELETE CASCADE;
 [   ALTER TABLE ONLY public.hotel_meal_plan DROP CONSTRAINT fk_hotel_has_meal_plan_meal_plan1;
       public          postgres    false    236    5089    230                        2606    116532 3   hotel_type_of_room fk_hotel_has_type_of_room_hotel1    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_type_of_room
    ADD CONSTRAINT fk_hotel_has_type_of_room_hotel1 FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.hotel_type_of_room DROP CONSTRAINT fk_hotel_has_type_of_room_hotel1;
       public          postgres    false    5071    233    227            !           2606    116537 :   hotel_type_of_room fk_hotel_has_type_of_room_type_of_room1    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_type_of_room
    ADD CONSTRAINT fk_hotel_has_type_of_room_type_of_room1 FOREIGN KEY (type_of_room_id) REFERENCES public.type_of_room(id) ON DELETE CASCADE;
 d   ALTER TABLE ONLY public.hotel_type_of_room DROP CONSTRAINT fk_hotel_has_type_of_room_type_of_room1;
       public          postgres    false    233    262    5127                       2606    116542 5   hotel_hotel_modality fk_hotel_id_hotel_hotel_modality    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_hotel_modality
    ADD CONSTRAINT fk_hotel_id_hotel_hotel_modality FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.hotel_hotel_modality DROP CONSTRAINT fk_hotel_id_hotel_hotel_modality;
       public          postgres    false    228    227    5071                       2606    116547 ?   accommodation_modality fk_hotel_modality_accommodation_modality    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT fk_hotel_modality_accommodation_modality FOREIGN KEY (hotel_modality_id) REFERENCES public.hotel_modality(id) ON DELETE CASCADE;
 i   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT fk_hotel_modality_accommodation_modality;
       public          postgres    false    232    5079    217                       2606    116552 >   hotel_hotel_modality fk_hotel_modality_id_hotel_hotel_modality    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_hotel_modality
    ADD CONSTRAINT fk_hotel_modality_id_hotel_hotel_modality FOREIGN KEY (hotel_modality_id) REFERENCES public.hotel_modality(id) ON DELETE CASCADE;
 h   ALTER TABLE ONLY public.hotel_hotel_modality DROP CONSTRAINT fk_hotel_modality_id_hotel_hotel_modality;
       public          postgres    false    228    232    5079            "           2606    116557 8   hours_kilometers fk_hours_kilometers_transport_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.hours_kilometers
    ADD CONSTRAINT fk_hours_kilometers_transport_modality1 FOREIGN KEY (modality_id) REFERENCES public.transport_modality(modality_id) ON DELETE CASCADE;
 b   ALTER TABLE ONLY public.hours_kilometers DROP CONSTRAINT fk_hours_kilometers_transport_modality1;
       public          postgres    false    5121    258    234                       2606    116562    hotel fk_id_provider_hotel    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT fk_id_provider_hotel FOREIGN KEY (id) REFERENCES public.provider(id_provider) ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.hotel DROP CONSTRAINT fk_id_provider_hotel;
       public          postgres    false    227    243    5097            -           2606    116567 0   service_provider fk_id_provider_service_provider    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_provider
    ADD CONSTRAINT fk_id_provider_service_provider FOREIGN KEY (id) REFERENCES public.provider(id_provider) ON DELETE CASCADE;
 Z   ALTER TABLE ONLY public.service_provider DROP CONSTRAINT fk_id_provider_service_provider;
       public          postgres    false    5097    243    253            4           2606    116572 >   transportation_provider fk_id_provider_transportation_provider    FK CONSTRAINT     �   ALTER TABLE ONLY public.transportation_provider
    ADD CONSTRAINT fk_id_provider_transportation_provider FOREIGN KEY (id) REFERENCES public.provider(id_provider) ON DELETE CASCADE;
 h   ALTER TABLE ONLY public.transportation_provider DROP CONSTRAINT fk_id_provider_transportation_provider;
       public          postgres    false    5097    260    243            5           2606    116577    user fk_id_rol    FK CONSTRAINT     ~   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk_id_rol FOREIGN KEY (id_rol) REFERENCES public.rol(id) ON DELETE CASCADE;
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT fk_id_rol;
       public          postgres    false    5101    247    265            0           2606    116582 B   transport_modality fk_modalidad_contratada_contrato_transportista1    FK CONSTRAINT     �   ALTER TABLE ONLY public.transport_modality
    ADD CONSTRAINT fk_modalidad_contratada_contrato_transportista1 FOREIGN KEY (carrier_contract_id) REFERENCES public.carrier_contract(id_contract) ON DELETE CASCADE;
 l   ALTER TABLE ONLY public.transport_modality DROP CONSTRAINT fk_modalidad_contratada_contrato_transportista1;
       public          postgres    false    258    221    5061            .           2606    116587 B   tourist_package_modality fk_modality_has_tourist_package_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.tourist_package_modality
    ADD CONSTRAINT fk_modality_has_tourist_package_modality1 FOREIGN KEY (modality_id) REFERENCES public.modality(id) ON DELETE CASCADE;
 l   ALTER TABLE ONLY public.tourist_package_modality DROP CONSTRAINT fk_modality_has_tourist_package_modality1;
       public          postgres    false    5091    257    238            /           2606    116592 I   tourist_package_modality fk_modality_has_tourist_package_tourist_package1    FK CONSTRAINT     �   ALTER TABLE ONLY public.tourist_package_modality
    ADD CONSTRAINT fk_modality_has_tourist_package_tourist_package1 FOREIGN KEY (tourist_package_id) REFERENCES public.tourist_package(id_tourist_package) ON DELETE CASCADE;
 s   ALTER TABLE ONLY public.tourist_package_modality DROP CONSTRAINT fk_modality_has_tourist_package_tourist_package1;
       public          postgres    false    257    255    5114            %           2606    116597 =   password_change_request fk_password_change_request_request_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.password_change_request
    ADD CONSTRAINT fk_password_change_request_request_id FOREIGN KEY (id) REFERENCES public.request(id) ON DELETE CASCADE;
 g   ALTER TABLE ONLY public.password_change_request DROP CONSTRAINT fk_password_change_request_request_id;
       public          postgres    false    241    5099    245                       2606    116602 @   accommodation_modality fk_planes_escogidos_contrato_alojamiento1    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT fk_planes_escogidos_contrato_alojamiento1 FOREIGN KEY (accommodation_contract_id) REFERENCES public.accommodation_contract(id_contract) ON DELETE CASCADE;
 j   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT fk_planes_escogidos_contrato_alojamiento1;
       public          postgres    false    217    216    5049            &           2606    116607    request fk_request_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.request
    ADD CONSTRAINT fk_request_user_id FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.request DROP CONSTRAINT fk_request_user_id;
       public          postgres    false    5129    265    245            '           2606    116612 '   season fk_season_accommodation_contract    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT fk_season_accommodation_contract FOREIGN KEY (accommodation_contract_id) REFERENCES public.accommodation_contract(id_contract) ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.season DROP CONSTRAINT fk_season_accommodation_contract;
       public          postgres    false    248    5049    216                       2606    116617 3   accommodation_modality fk_selected_plans_meal_plan1    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT fk_selected_plans_meal_plan1 FOREIGN KEY (meal_plan_id) REFERENCES public.meal_plan(id) ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT fk_selected_plans_meal_plan1;
       public          postgres    false    217    236    5089                       2606    116622 6   accommodation_modality fk_selected_plans_type_of_room1    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_modality
    ADD CONSTRAINT fk_selected_plans_type_of_room1 FOREIGN KEY (type_of_room_id) REFERENCES public.type_of_room(id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.accommodation_modality DROP CONSTRAINT fk_selected_plans_type_of_room1;
       public          postgres    false    5127    262    217            ,           2606    116627 .   service_modality fk_service_modality_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.service_modality
    ADD CONSTRAINT fk_service_modality_modality1 FOREIGN KEY (modality_id) REFERENCES public.modality(id) ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.service_modality DROP CONSTRAINT fk_service_modality_modality1;
       public          postgres    false    252    5091    238            2           2606    116632 N   transport_modality_vehicle fk_transport_modality_id_transport_modality_vehicle    FK CONSTRAINT     �   ALTER TABLE ONLY public.transport_modality_vehicle
    ADD CONSTRAINT fk_transport_modality_id_transport_modality_vehicle FOREIGN KEY (transport_modality_id) REFERENCES public.transport_modality(modality_id) ON DELETE CASCADE;
 x   ALTER TABLE ONLY public.transport_modality_vehicle DROP CONSTRAINT fk_transport_modality_id_transport_modality_vehicle;
       public          postgres    false    259    258    5121            1           2606    116637 2   transport_modality fk_transport_modality_modality1    FK CONSTRAINT     �   ALTER TABLE ONLY public.transport_modality
    ADD CONSTRAINT fk_transport_modality_modality1 FOREIGN KEY (modality_id) REFERENCES public.modality(id) ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.transport_modality DROP CONSTRAINT fk_transport_modality_modality1;
       public          postgres    false    238    258    5091                       2606    116642    administrator fk_user_id_admin    FK CONSTRAINT     �   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT fk_user_id_admin FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.administrator DROP CONSTRAINT fk_user_id_admin;
       public          postgres    false    265    5129    220                       2606    116647    dependent fk_user_id_dependent    FK CONSTRAINT     �   ALTER TABLE ONLY public.dependent
    ADD CONSTRAINT fk_user_id_dependent FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.dependent DROP CONSTRAINT fk_user_id_dependent;
       public          postgres    false    225    265    5129            #           2606    116652    manager fk_user_id_manager    FK CONSTRAINT     �   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT fk_user_id_manager FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.manager DROP CONSTRAINT fk_user_id_manager;
       public          postgres    false    5129    265    235            $           2606    116657 ,   package_designer fk_user_id_package_designer    FK CONSTRAINT     �   ALTER TABLE ONLY public.package_designer
    ADD CONSTRAINT fk_user_id_package_designer FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.package_designer DROP CONSTRAINT fk_user_id_package_designer;
       public          postgres    false    240    5129    265            3           2606    116662 C   transport_modality_vehicle fk_vehicle_id_transport_modality_vehicle    FK CONSTRAINT     �   ALTER TABLE ONLY public.transport_modality_vehicle
    ADD CONSTRAINT fk_vehicle_id_transport_modality_vehicle FOREIGN KEY (vehicle_id) REFERENCES public.vehicle(id_vehicle) ON DELETE CASCADE;
 m   ALTER TABLE ONLY public.transport_modality_vehicle DROP CONSTRAINT fk_vehicle_id_transport_modality_vehicle;
       public          postgres    false    5132    266    259            6           2606    116667 +   vehicle fk_vehiculo_prestatario_transporte1    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT fk_vehiculo_prestatario_transporte1 FOREIGN KEY (transportation_provider_id) REFERENCES public.transportation_provider(id) ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.vehicle DROP CONSTRAINT fk_vehiculo_prestatario_transporte1;
       public          postgres    false    5125    260    266            �   !   x�3�ര�25�442�25�446����� 3��      �   F   x����0Cѳ��!T����O�聝�}�I��N�-R3l����t��R��3M��*�������B�      �   �  x��U�r�6����l���M�t�-2�d=��"�抄dd@�HN��r;���\����ƕ$�܃s�}h��j+�u�7�Se����2�S����
�[-���m�����a��(l9�W�rW��bD��w��s��r9{3�l�<Oa��g�����1��{��|%�M0E�#o\ 0h�+�����
cY4N���>���*F�#@3�}�q޾7�g��mĭ��ǯ��и'R�Dc�ƁI1�.��*2^+k�^��D� ԷyRn���%<�������1W��,%��V��̨���#�Mx0�Q5�����4^iˏ���K/�@�y̿�]n��NŻk*�jZU�ֺN�wr�z�:�%���ykQ�5��?6&�dk����CL����.V&b�?��	�����ӠG1;�Y��Q~��]@�L�׭�Q�g��l�P�j�`]��I�{�(8V�ε�~��F\��h�� ��:XD���ض_M���v->��q��ԡiV�3_)kB+S9ۙ؛Q��7~���e�r=�d;���q�H!wMe�OYP�w$���*\}2�*S�f^��Y�=����s'��� �m��V�^d��})F��o콁�U(��mQ��4���k�K@�^��h��0�M��ؙ�SE���TSC�������g��}��ۚ-�y��
�o���IO����\$g:J��*�<A�"��ҞB�D���Ɖ��CǷ�RWU���0ߴ/s���?��,v^`wxB%��Aî�C�F�tūѦ��z���Vf��Z`�'*x3��}@],'{�g*n�?Dˌ� � #0i_<`������S���Oq���ƨø$Pޒ��E�:8_b�xQp���W��N����r��G�&�ҟ	u�����x�ӡ�V��e��u5�<�����w�b5�1Afkq�i���ѹ�MZ�q��|���s"���X�      �      x�37�����       �      x�3��0�25�0����� v{      �   �  x��SAn�0<K��\H�d+��ȭ衽�!i��$�$!����|�+�N��k�2����rV,3^�jQ�^��0iz`Jẃ� ������ю��Ȑm���Y����lR^�Q~�U<�O힍�g�a��#yUA�LkZkl�UJ��VA0v8���W{��	&�"�T	��O-��*�*r6��^w*V��x9��AM;��(��\$)T���t�L�t�υ���6��ϭ�j$�h���i�H�#��{4Ӟ��#�X��_��v������z#-� P"٪�Q"�DBFR�$������� ����c�	��N���"�����kf7XV�����(�m�7z���u��m��<�E0��~�!	��wg�v�C3æ,:T����:�AsAe��38*V�}wF�:��~�.h*ި��N!����7C������`���o�\�<��Oi�P�ٍ�?��y_�<����O      �   "   x�340�45 !#.CcN326������ J�u      �      x������ � �      �   %   x�340��HLJ�K�K,JLI-��45 "�=... ���      �   u  x�eU�r�6<C_�� �f��Wv��Zo|H�2�`/�p�P��o���R��Xz@Q��*�h�����ݽoC�S+>��~hB�J�-mB��:ԃ��,�@�[�������N��r)�4>��E�;�n���z_?S���K��-���^��ȔN\WÆ6B	�
Y�<��,�+_W���[.�%�޷�Uh�ˡ��Ln��,����xO�K�O�mURw��Z#t��<w&��F$O �����e����O�('d*�5��Ԇ�K�Sy.���MK�V�^\S]�DIH�	��B�D�����r�b�^�Q���Hf�#�A�?U4p�s�R��,�K[5�$�ʡ�y挒��Jg"r���}S5�P�)�~�˫4��T��z*���S-�m���X6U�E�;��R�W�.zb&l�\|�~0'� r�Ea"�b6!�յL�%>1!�iks�e�bM�4�m,�o�:�xLӊ �@��7q��k�����Ke��tmx��GV�~�Yq�V&�f�h�|��j_��i��$L:Iy�)��G-��f��'uʤ�㹱3�"#�	������#'V�G�0jOu�p���G$�X�l��*}��{��٬��MG��xC�*�Ig�4F�c� �2�.3���"F�}�Z�A
#
q;�ZC^f�x�:��)0��o|�,1�����b֋\k�Da��c�^r�E� ��T�!�f���~{�����<3�!����%�U]�����W�W��Kh'T4~~�Z� R�����6h{&ѫ�*t'� !�{��!��V�-��·�c�'��<����2$DF._�|?W�
��0E*�{�hw)G&z�7���=짇D���5#ټp��Ii��揄o�q�P�0��p��!%+:����Y'A�H���e5��B|�!H�W�)0=�H�BG3�2Ɲ0�z^�{8Sd�Ydx���7���x���� b�<BX@��z���Ԉ�O�#3J�s����A�X����
��[^�_|��7�"�|�w;
hO��<�X�W�S�G�k���i�E���p?wh<3�X�0whs�7<��=��}>���P_WM	㜗��t�5N��\dt=�퉎��íV˼\���o���_e-�o      �   �   x�-���0߸�L쓱�K��#���s+��<�y�e�c�ȴM۴M۴M۲-۲-۲��s�~4'\PM������/�+I��$�7�Va[X{�뼹����=�<s����D$bK�z���3�L0CѢ��`���t��rZ��]������1�k$>1      �   x   x����0�w\�!�H�%��q�~�����u���վ��3WdR�M��WtO)��Fi� 9ݖ��%�@I$�D�𫩨}��s*jp~>��B�+�
�B�+�
y��[k�V-,�      �   V   x�3��)�KTHIU��L��2�t��I�2���,.9��(39�˄�59?���\ǔ3$?%_�3/9�43%��$YRZ�Y������� N#m      �   �   x�-��C!�s(&
`�K��#x~.+����-�5Zf]Yש�ܫ�q���Ɲw���ѧ>��O}�K_�ҷ���o=��C=��SO��ϟ�~����.ny׻�uK���zi)�b)�b)�b)�b)�b)�b)�b)6b#6b�Qu�wk�z�<H      �      x�340�41�44�42�4����� ��      �      x�347�24������� ��      �   :   x�3�tI-N�,���2��O�W��K�)�L��2�t��-M-���2�tN�K����� ���      �   s   x�u�;� E�V�
�~)]���6-HTw�%������J���Y��-�f�#u���c"�����`�'N'%F?��|���\b�0�E&��&��%�WX�S�JS.�|5�{      �      x�34��2�0������ ��      �      x������ � �      �   L  x�mT�r�:�����H�X*r���K#+�iV�F�*���e
�����CY�@;��Y���ٳ;�ŵ5ڲ\��Wb��~�y�d�Sg]�rZ/I\�<�%YJ�J��_��ÿ��1��\xڰ�7�7��J\R �HH��������x��Ɛ��H����.tJ���~��:X��}�~$�(=#��x�dⰱx�d��'y��%{'�1K�w��B;KF�{�1O�C�$qC6hZ��W���DprQ��̪n��L�\�أ�+^y*|"���;:�D�zhki�щ������˥��p�+�6|lZ�R1g�<�Q���z�Xh��'\ݷ��m4#y�<S%fڒ#�ͳK�TLlpw��5���x���C�.W�Q��Ճ_;��9�x���w�ײ�������@ܸ�L�Z�+�Eړ���cU�#����Z����y�^�mŵi~�VH��%C��Z�W�e��q�"P�4.�z��0�"雧]F���|j�qɾ#\��(r��\^�S��f�
��Q�L�z���[-)��m̔�k���W��~=$�-?{��A~0����P��K��3�1��t��dl[c��t��-T�ڙÁ[ۈc�4�=[xmuI�����6o�I���k�BL��o)�|���m;#Lb���=B7ܧ8�TJ9�J�q�;]Bx�-�A��e��8ܕs��l��He�pv�9qZ�y���(��>��m��h6�\��	�@�  �v��r�7�}y�a��.q�5�J�au!f}��vU��T�;�E��H�8LI�7�������5���G,;6�������l�a�5"�Ԑ���$���9Q      �      x������ � �      �   B   x�3�tL����,.)J,�/�2��M�KLO-�2�tI-H�KI�+�2�HL�
��g��ec���� m�O      �   �   x�e�;�0��>�^�(v>J���AE�I96r�H9g�b$J��tS�>�
A�ҡ�DD"aQ�">ŜŜ����:���^�Xh	�������H����lM��{��� �i ���5�c��B��ۈ�d�|X��ZX_ռ�3��qZi�և;NJS�7�V7^��B%���_h���iA)}�ZO]      �   *   x��I 0���Jin.口v�Ɖ�i�"�vIW/� a�7      �   k   x�]��	AD�s)�-����p������ID!T&,�4u܎;�Ь�}x�5M_m��M��|nW��fMR)�"�o׽������FJ�>H����I�� c#�      �   9   x�Ʊ�0���	D9��]��Qq����)"�d��M��$�jh�Xc����0L      �   L   x�34�H,,M-IU0�4�4�4202�50"8�ؐ���̄��2s�2SNS$eFf(�,���81��B���qqq �L �      �   A   x�%���0C�s=L�CB�.��|�O�i|8A�"���Bch��0������n�҆����PZ      �   J   x�340�4��t�/.Q�����M-I-*�240�.l�ij��_ZT�*n
w-.IL��,�HMQ�/-I����� �x�      �   ,   x�340�46�2Q�@��3��L8�- �%�2��LA�=... "�	1      �   5   x�ʹ  �\�0��L/�_G���PMՒ��(�#�10�3�<�2t����z      �   1   x�3���K�,�L)M��2�t�O�I�2�)�, 2L8�K3KR�b���� 6(      �   �  x�m��j1F�sߥE�lI^�%Y?)�.���M����Yh��:�w�������zz���uΐR	��W"@KM�u�E�^�i�W5ՊS�N!Tə*��������b�>~F��C��
�5����k\�?���cYׯ�~'Η��5�'�[e���=@���qfƸY|m��JQ�H&�(:n~g/�<T[Fr9B�����f������~,ߑ�o��l��a���;\@�Bl�� q��` �#jk.gf;�A_�vY{O]�{0�4�@��#+W6&u��1L���c�����-���/a���~�b�'�d�;42�����z�2ޓp0���1=�"�|..F*�^mZ��F��\7���C�s6���ُ�lO�������-��'�D�D�I-��wAN�؈ҝ��v"Ɛ���Β΢�q�1 
R�O�i<�ƱA���>^h-���6G�������n�?�^��      �   �  x�}��N#G��k��/0Q�TUw_�V�"XKD�&7�f�,Ȍ�%�Zy��XN���Y0�`>��>��;2-C=e�_���}�%/�)8_Z�[�?��B���h�u������]��!<8�m������4e������S0�l�����h飓��:��I(����1A)��D|EY�n|z���=�Ȫ�ZW��,Ps1J0�H���t�;Zk�̑�өb���8In��:eU�����֫A��%sP9A��sC�nPe�tL�����X��i]F�̴�$�\��CY�z���x�`~Dk)�˯��z�r�8؟QX�7�ød���	#ԏ9x"�Zg�͓B7��������F�Y�4�9vޝ��ěf��yQg3��VI%[��ѯ�n�0�Yࠑ���K$����@,QS�I�X��L@z�:ޟ|�R}�9�!$�;���>�0��y�N���1��N�z�ٍߏ{�c��{��l�X�/�ۊ�}N�Su��� ��O�aq�?7�i���p��E���qF��(AI�E����$h���h~�'m�uD����=����~�Ń��J��\{��>��p����Ōٝ��A`�}��	}��?��aqՏO���cY"$EHX��c�W��'n:J4�$��ِ���n8�g��HH�H5M��n<���{���Ϻ�O��\b�<%`�y�V�s�Ε"�*1����[�yq;��9׀~[o�vBR>�V.���[d��U]��s����=m��g�a�2'3B�M�a͒|�n�O����.�ت�1��FD����^���"�z��F":�@��B�Ǘ��vq5,|��tFx8��r��|��ͨz��u0�с�<�<	��oO�x���n3�79
S�?@�>lӝM(dJ���4��s��     