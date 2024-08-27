create sequence public.payment_seq start with 1 increment by 1;
create sequence public.event_seq start with 1 increment by 1;

create table public.payment (payment_id integer not null, bic text, iban text, currency text, amount integer, guid uuid , primary key (payment_id));
create table public.event (event_id integer not null, public_id uuid, body jsonb, primary key (event_id));