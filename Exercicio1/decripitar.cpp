#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

int main() {
    const int linhas = 6;
    const int colunas = 5;
    char matriz[linhas][colunas];

    string fraseCriptografada;
    cout << "Digite a frase criptografada: ";
    getline(cin, fraseCriptografada);

    int k = 0;
    for (int j = 0; j < colunas; j++) {
        for (int i = 0; i < linhas; i++) {
            matriz[i][j] = fraseCriptografada[k++];
        }
    }

    cout << "\nMatriz preenchida:\n";
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            //para a impressão ficar legal
            cout << setw(2) << matriz[i][j];
        }
        cout << endl;
    }

    string fraseDecodificada;
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            fraseDecodificada += matriz[i][j];
        }
    }

    cout << "\nFrase decodificada:\n" << fraseDecodificada << endl;
    //Aauladeimd0703ehmuitolegal....

    return 0;
}
