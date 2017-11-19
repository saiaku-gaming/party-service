CREATE TABLE party (
	party_id SERIAL NOT NULL,
	leader TEXT NOT NULL,
	active BOOLEAN NOT NULL
);

CREATE TABLE party_invite (
	party_invite_id SERIAL NOT NULL,
	party_id INTEGER NOT NULL,
	sender TEXT NOT NULL,
	receiver TEXT NOT NULL
);

CREATE TABLE party_member (
	party_member_id SERIAL NOT NULL,
	party_id INTEGER NOT NULL,
	username TEXT NOT NULL
);