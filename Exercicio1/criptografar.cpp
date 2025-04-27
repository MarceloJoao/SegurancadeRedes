#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

int main() {
    const int linhas = 6;
    const int colunas = 5;
    char matriz[linhas][colunas];

    string fraseOriginal;
    cout << "Digite a frase original: ";
    getline(cin, fraseOriginal);

    int k = 0;
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            matriz[i][j] = fraseOriginal[k++];
        }
    }

    cout << "\nMatriz preenchida :\n";
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            cout << setw(2) << matriz[i][j];
        }
        cout << endl;
    }

    string fraseCriptografada;
    for (int j = 0; j < colunas; j++) {
        for (int i = 0; i < linhas; i++) {
            fraseCriptografada += matriz[i][j];
        }
    }

    cout << "\nFrase criptografada:\n" << fraseCriptografada << endl;
    return 0;
}
