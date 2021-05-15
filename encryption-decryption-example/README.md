# generate the encryption-decryption keys

### please refer to below documentation to create the keys.

https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/generating-a-new-gpg-key

```
$gpg --full-generate-key
```
gpg (GnuPG) 2.2.19; Copyright (C) 2019 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Please select what kind of key you want:
(1) RSA and RSA (default)
(2) DSA and Elgamal
(3) DSA (sign only)
(4) RSA (sign only)
(14) Existing key from card
```
Your selection? 1
```
RSA keys may be between 1024 and 4096 bits long.
What keysize do you want? (3072)
Requested keysize is 3072 bits
Please specify how long the key should be valid.
0 = key does not expire
<n>  = key expires in n days
<n>w = key expires in n weeks
<n>m = key expires in n months
<n>y = key expires in n years
```
Key is valid for? (0) 0
```
Key does not expire at all
```
Is this correct? (y/N) y
```
GnuPG needs to construct a user ID to identify your key.
```
Real name: kodtodya-talks
Email address: kodtodya.talks@testdomain.com
Comment: these keys are created to show as example
You selected this USER-ID:
    "kodtodya-talks (these keys are created to show as example) <kodtodya.talks@testdomain.com>"
```
Change (N)ame, (C)omment, (E)mail or (O)kay/(Q)uit? o
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
gpg: key E9C4F278F749C63E marked as ultimately trusted
gpg: revocation certificate stored as '/home/kodtodya/.gnupg/openpgp-revocs.d/40CCF6FBFB209B78558A0EA9E9C4F278F749C63E.rev'
public and secret key created and signed.

pub   rsa3072 2020-11-18 [SC]
40CCF6FBFB209B78558A0EA9E9C4F278F749C63E
uid                      kodtodya-talks (these keys are created to show as example) <kodtodya.talks@testdomain.com>
sub   rsa3072 2020-11-18 [E]

```
As and when you have to enter password, please enter password as: kodtodya
```

## Ceck the list of keys

```
gpg --list-secret-keys kodtodya.talks@testdomain.com
``` 

## Export public & private keys & use password as 'kodtodya'
```
gpg --export-secret-keys kodtodya.talks@testdomain.com > /home/kodtodya/Downloads/_test/kodtodya-talks-private.key

gpg --armor --export kodtodya.talks@testdomain.com > /home/kodtodya/Downloads/_test/kodtodya-talks-public.key
```
