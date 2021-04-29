import random
from math import pow


def gcd(a, b):
    if a < b:
        return gcd(b, a)
    elif a % b == 0:
        return b
    else:
        return gcd(b, a % b)


def gen_key(q):

    key = random.randint(pow(10, 20), q)
    while gcd(q, key) != 1:
        key = random.randint(pow(10, 20), q)

    return key

# Modular exponentiation


def power(a, b, c):
    x = 1
    y = a

    while b > 0:
        if b % 2 == 0:
            x = (x * y) % c
        y = (y * y) % c
        b = int(b / 2)

    return x % c

# Asymmetric encryption


def encrypt(message, q, h, g):

    en_message = []

    k = gen_key(q)  # Private key for sender
    s = power(h, k, q)
    p = power(g, k, q)

    for i in range(0, len(message)):
        en_message.append(message[i])

    print("g^k used : ", p)
    print("g^ak used : ", s)
    for i in range(0, len(en_message)):
        en_message[i] = s * ord(en_message[i])

    return en_message, p


def decrypt(en_message, p, key, q):

    dr_message = []
    h = power(p, key, q)
    for i in range(0, len(en_message)):
        dr_message.append(chr(int(en_message[i]/h)))

    return dr_message


def main():

    message = 'SarathyWeb'
    print("Original Message :", message)

    q = random.randint(pow(10, 20), pow(10, 50))
    g = random.randint(2, q)

    key = gen_key(q)  # Private key for receiver
    h = power(g, key, q)
    print("g used : ", g)
    print("g^a used : ", h)

    en_message, p = encrypt(message, q, h, g)
    dr_message = decrypt(en_message, p, key, q)
    dmessage = ''.join(dr_message)
    print("Decrypted Message :", dmessage)


if __name__ == '__main__':
    main()
